package program.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import program.deck.Card;
import program.deck.hero.Hero;
import program.deck.minion.Goliath;
import program.deck.minion.Minion;
import program.deck.minion.Warden;
import program.dependencies.Player;
import program.dependencies.Table;

import java.util.ArrayList;

public final class UseAttackHero implements Command {
    private final MyCoordinates cardAttacker;
    public UseAttackHero(final ActionsInput input) {
        cardAttacker = new MyCoordinates(input.getCardAttacker());
    }

    /**
     * Method applies attack on opponent player's hero and saves errors
     * in output. The hero's life decreases by the number of attack-damage
     * of the minion attacking it. If the hero's life becomes 0 or less
     * the game ends and the current player wins.
     *
     * @param output stores output of action
     * @param player stores current status of players' hands, decks,
     *               heroes, mana available, round, won games, and
     *               total games played
     * @param table stores current status of the cards on the table
     */
    @Override
    public void action(final ArrayNode output, final ArrayList<Player> player,
                             final Table table) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("command", "useAttackHero");
        node.set("cardAttacker", mapper.valueToTree(cardAttacker));

        // Check for errors
        if (cardFrozenError(node, table) || cardUsedError(node, table)
                || tankCardError(node, table, player)) {
            output.add(node);
            return;
        }

        // Use ability
        int playerIdx = player.get(0).getPlayerIdx();
        if (playerIdx == 2) {
            playerIdx = 0;
        }
        Card attacker = table.getTable().get(cardAttacker.getX()).get(cardAttacker.getY());
        Hero attacked = player.get(playerIdx).getHero();
        attacked.setHealth(attacked.getHealth() - attacker.getAttackDamage());
        ((Minion) attacker).setAttacked(1);

        // Check if hero was killed and if so, update won games of player
        if (attacked.getHealth() <= 0) {
            Player currentPlayer = player.get((playerIdx + 1) % 2);
            currentPlayer.setWonGames(currentPlayer.getWonGames() + 1);

            ObjectNode node2 = mapper.createObjectNode();
            if (playerIdx == 1) {
                node2.put("gameEnded", "Player one killed the enemy hero.");
            } else {
                node2.put("gameEnded", "Player two killed the enemy hero.");
            }
            output.add(node2);
        }
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

    // Check if attacked card is tank and if not, check if enemy has tank card
    private boolean tankCardError(final ObjectNode node, final Table table,
                                  final ArrayList<Player> player) {
        int playerIdx = player.get(0).getPlayerIdx();
        int rowIdx;
        // Find front row of enemy to search for tank cards
        if (playerIdx == 1) {
            rowIdx = 1;
        } else {
            rowIdx = 2;
        }

        // Parse the row to find tank cards
        Card card;
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
