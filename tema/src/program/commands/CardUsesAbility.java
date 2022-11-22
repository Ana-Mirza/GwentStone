package program.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import program.deck.Card;
import program.deck.minion.Disciple;
import program.deck.minion.Goliath;
import program.deck.minion.Minion;
import program.deck.minion.Warden;
import program.deck.minion.TheRipper;
import program.deck.minion.Miraj;
import program.deck.minion.TheCursedOne;
import program.dependencies.Player;
import program.dependencies.Table;

import java.util.ArrayList;
public class CardUsesAbility implements Command {
    private final MyCoordinates cardAttacker;
    private final MyCoordinates cardAttacked;
    public CardUsesAbility(final ActionsInput input) {
        cardAttacker = new MyCoordinates(input.getCardAttacker());
        cardAttacked = new MyCoordinates(input.getCardAttacked());
    }

    /**
     * Method applies ability of a minion card on
     * the table after checking for errors, and saves output.
     *
     * @param output stores the output of the command
     * @param player stores current status of players' hands, decks,
     *               heroes, mana available, round, won games, and
     *               total games played
     * @param table stores current status of the cards on the table
     */
    @Override
    public final void action(final ArrayNode output, final ArrayList<Player> player,
                             final Table table) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("command", "cardUsesAbility");
        node.set("cardAttacker", mapper.valueToTree(cardAttacker));
        node.set("cardAttacked", mapper.valueToTree(cardAttacked));

        // Check for errors
        if (cardFrozenError(node, table) || cardUsedError(node, table)
                || alliedCard(node, table, player) || enemyCard(node, table, player)
                || tankCardError(node, table, player)) {
            output.add(node);
            return;
        }

        // Apply ability
        Card attacker = table.getTable().get(cardAttacker.getX()).get(cardAttacker.getY());
        Card attacked = table.getTable().get(cardAttacked.getX()).get(cardAttacked.getY());
        ((Minion) attacker).ability(attacked);

        // Check if health became 0 to remove from table
        if (attacked.getHealth() == 0) {
            table.getTable().get(cardAttacked.getX()).remove(cardAttacked.getY());
        }
        // Set attack field = 1 to signify that it used its ability in current turn
        ((Minion) attacker).setAttacked(1);
    }

    // Check if attacker card is frozen
    private boolean cardFrozenError(final ObjectNode node, final Table table) {
        Card card = table.getTable().get(cardAttacker.getX()).get(cardAttacker.getY());
        if (((Minion) card).getFrozen() == 1) {
            node.put("error", "Attacker card is frozen.");
            return true;
        }
        return false;
    }

    // Check if card was already used this turn
    private boolean cardUsedError(final ObjectNode node, final Table table) {
        Card card = table.getTable().get(cardAttacker.getX()).get(cardAttacker.getY());
        if (((Minion) card).getAttacked() == 1) {
            node.put("error", "Attacker card has already attacked this turn.");
            return true;
        }
        return false;
    }

    // Check if attacker is Disciple and attacked card belongs to player
    private boolean alliedCard(final ObjectNode node, final Table table,
                               final ArrayList<Player> player) {
        Card attacker = table.getTable().get(cardAttacker.getX()).get(cardAttacker.getY());
        int playerIdx = player.get(0).getPlayerIdx();

        if (attacker instanceof Disciple && playerIdx == 1
                && (cardAttacked.getX() == 0 || cardAttacked.getX() == 1)) {
            node.put("error", "Attacked card does not belong to the current player.");
            return true;
        } else if (attacker instanceof Disciple && playerIdx == 2
                && (cardAttacked.getX() == 2 || cardAttacked.getX() == 3)) {
            node.put("error", "Attacked card does not belong to the current player.");
            return true;
        }
        return false;
    }

    // Check if attacked card belongs to enemy for The Ripper, Miraj, and The Cursed One
    private boolean enemyCard(final ObjectNode node, final Table table,
                              final ArrayList<Player> player) {
        Card attacker = table.getTable().get(cardAttacker.getX()).get(cardAttacker.getY());
        int playerIdx = player.get(0).getPlayerIdx();

        if ((attacker instanceof TheRipper || attacker instanceof Miraj
                || attacker instanceof TheCursedOne)
                && (playerIdx == 1 && (cardAttacked.getX() == 2 || cardAttacked.getX() == 3))) {
            node.put("error", "Attacked card does not belong to the enemy.");
            return true;
        } else if ((attacker instanceof TheRipper || attacker instanceof Miraj
                || attacker instanceof TheCursedOne)
                && (playerIdx == 2 && (cardAttacked.getX() == 0 || cardAttacked.getX() == 1))) {
            node.put("error", "Attacked card does not belong to the enemy.");
            return true;
        }
        return false;
    }

    // Check if attacked card is tank and if not, check if enemy has tank card
    private boolean tankCardError(final ObjectNode node, final Table table,
                                  final ArrayList<Player> player) {
        // Check if card is of type tank or if attacker is Disciple
        Card card = table.getTable().get(cardAttacked.getX()).get(cardAttacked.getY());
        Card attacker = table.getTable().get(cardAttacker.getX()).get(cardAttacker.getY());
        if (card instanceof Goliath || card instanceof Warden || attacker instanceof Disciple) {
            return false;
        }

        int playerIdx = player.get(0).getPlayerIdx();
        int rowIdx;
        // Find front row of enemy to search for tank cards
        if (playerIdx == 1) {
            rowIdx = 1;
        } else {
            rowIdx = 2;
        }

        // Parse the row to find tank cards
        for (int i = 0; i < table.getTable().get(rowIdx).size(); i++) {
            card = table.getTable().get(rowIdx).get(i);
            if (card instanceof Goliath || card instanceof Warden) {
                node.put("error", "Attacked card is not of type 'Tank'.");
                return true;
            }
        }
        return false;
    }
}
