package program.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import program.deck.Card;
import program.deck.environment.Environment;
import program.deck.minion.TheRipper;
import program.deck.minion.Miraj;
import program.deck.minion.Goliath;
import program.deck.minion.Warden;
import program.deck.minion.Sentinel;
import program.deck.minion.TheCursedOne;
import program.deck.minion.Berserker;
import program.deck.minion.Disciple;
import program.dependencies.Player;
import program.dependencies.Table;

import java.util.ArrayList;

public class PlaceCard implements Command {
    private final int handIdx;

    public PlaceCard(final ActionsInput input) {
        handIdx = input.getHandIdx();
    }

    /**
     * Method places specified card on table if card is valid and
     * saves output if there is an error.
     *
     * @param output stores output of action
     * @param player stores current status of players' hands, decks,
     *               heroes, mana available, round, won games, and
     *               total games played
     * @param table stores current status of the cards on the table
     */
    public final void action(final ArrayNode output, final ArrayList<Player> player,
                             final Table table) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("command", "placeCard");
        node.put("handIdx", handIdx);

        // Write output in case of error
        if (cardTypeError(node, player) || manaError(node, player)
                || rowError(node, player, table)) {
            output.add(node);
        }
    }

    // Check if card type is valid
    private boolean cardTypeError(final ObjectNode node, final ArrayList<Player> player) {
        int index = (player.get(0).getPlayerIdx() + 1) % 2;
        Card card = player.get(index).getHand().getCards().get(handIdx);

        if (card instanceof Environment) {
            node.put("error", "Cannot place environment card on table.");
            return true;
        }
        return false;
    }

    // Check if player has enough mana to place card on table
    private boolean manaError(final ObjectNode node, final ArrayList<Player> player) {
        int index = (player.get(0).getPlayerIdx() + 1) % 2;
        int playerMana = player.get(index).getMana();
        Card card = player.get(index).getHand().getCards().get(handIdx);
        int cardMana = card.getMana();

        if (cardMana > playerMana) {
            node.put("error", "Not enough mana to place card on table.");
            return true;
        }
        return false;
    }

    // Check if row has place for card
    private boolean rowError(final ObjectNode node, final ArrayList<Player> player,
                             final Table table) {
        int playerIdx = (player.get(0).getPlayerIdx() + 1) % 2;
        Card card = player.get(playerIdx).getHand().getCards().get(handIdx);

        if (card instanceof TheRipper || card instanceof Miraj
                || card instanceof Goliath || card instanceof Warden) {
            // Find row
            int rowIdx;
            if (playerIdx == 0) {
                rowIdx = 2;
            } else {
                rowIdx = 1;
            }

            ArrayList<Card> row = table.getTable().get(rowIdx);
            if (row.size() == 5) {
                node.put("error", "Cannot place card on table since row is full.");
                return true;
            }

            // Place card on table and erase from hand
            table.getTable().get(rowIdx).add(card);
            player.get(playerIdx).getHand().getCards().remove(handIdx);
            player.get(playerIdx).setMana(player.get(playerIdx).getMana() - card.getMana());
            return false;
        }
        if (card instanceof Sentinel || card instanceof Berserker
                || card instanceof TheCursedOne || card instanceof Disciple) {
            // Find row
            int rowIdx;
            if (playerIdx == 0) {
                rowIdx = 3;
            } else {
                rowIdx = 0;
            }

            ArrayList<Card> row = table.getTable().get(rowIdx);
            if (row.size() == 5) {
                node.put("error", "Cannot place card on table since row is full.");
                return true;
            }
            // Place card on table and erase from hand
            table.getTable().get(rowIdx).add(card);
            player.get(playerIdx).getHand().getCards().remove(handIdx);
            player.get(playerIdx).setMana(player.get(playerIdx).getMana() - card.getMana());
        }
        return false;
    }
}
