package program.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import program.deck.Card;
import program.deck.environment.Environment;
import program.deck.environment.HeartHound;
import program.dependencies.Player;
import program.dependencies.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UseEnvironmentCard implements Command {
    private final int handIdx;
    private final int affectedRow;
    public UseEnvironmentCard(final ActionsInput input) {
        handIdx = input.getHandIdx();
        affectedRow = input.getAffectedRow();
    }

    /**
     * Method applies the use of an environment card in the current
     * player's hand over the cards on a chosen row on the table, and
     * saves in output any errors.
     *
     * @param output stores output of action
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
        node.put("command", "useEnvironmentCard");
        node.put("handIdx", handIdx);
        node.put("affectedRow", affectedRow);

        if (indexError(node, player) || manaError(node, player)
                || rowError(node, player) || rowFullError(node, player, table)) {
            output.add(node);
            return;
        }

        // Use environment card
        int index = (player.get(0).getPlayerIdx() + 1) % 2;
        Card card = player.get(index).getHand().getCards().get(handIdx);
        if (card instanceof HeartHound) {
            // find mirror row
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, 3);
            map.put(1, 2);
            map.put(2, 1);
            map.put(3, 0);

            int mirrorRow = map.get(affectedRow);
            ((HeartHound) card).ability(table.getTable().get(affectedRow),
                                        table.getTable().get(mirrorRow));
        } else {
            ((Environment) card).ability(table.getTable().get(affectedRow));
        }

        // Delete card from player's hand and decrease mana
        player.get(index).setMana(player.get(index).getMana() - card.getMana());
        player.get(index).getHand().getCards().remove(handIdx);
    }

    // Check if chosen card is of type Environment
    private boolean indexError(final ObjectNode node, final ArrayList<Player> player) {
        int index = (player.get(0).getPlayerIdx() + 1) % 2;
        if (player.get(index).getHand().getCards().get(handIdx) instanceof Environment) {
            return false;
        }

        node.put("error", "Chosen card is not of type environment.");
        return true;
    }

    // Check if player has enough mana to use card
    private boolean manaError(final ObjectNode node, final ArrayList<Player> player) {
        int index = (player.get(0).getPlayerIdx() + 1) % 2;
        int playerMana = player.get(index).getMana();
        int cardMana = player.get(index).getHand().getCards().get(handIdx).getMana();

        if (cardMana > playerMana) {
            node.put("error", "Not enough mana to use environment card.");
            return true;
        }

        return false;
    }

    // Cehck if chosen row belongs to enemy
    private boolean rowError(final ObjectNode node, final ArrayList<Player> player) {
        int playerIdx = player.get(0).getPlayerIdx();

        if ((playerIdx == 1 && (affectedRow == 2 || affectedRow == 3))
                || (playerIdx == 2 && (affectedRow == 0 || affectedRow == 1))) {
            node.put("error", "Chosen row does not belong to the enemy.");
            return true;
        }
        return false;
    }

    // Check if mirror row is full
    private boolean rowFullError(final ObjectNode node, final ArrayList<Player> player,
                                 final Table table) {
        // check if environment card is Heart Hound
        int index = (player.get(0).getPlayerIdx() + 1) % 2;
        Card card = player.get(index).getHand().getCards().get(handIdx);

        int mirrorRow;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 3);
        map.put(1, 2);
        map.put(2, 1);
        map.put(3, 0);

        mirrorRow = map.get(affectedRow);
        if (card instanceof HeartHound
                && table.getTable().get(mirrorRow).size() == 5) {
            node.put("error", "Cannot steal enemy card since the player's row is full.");
            return true;
        }
        return false;
    }
}
