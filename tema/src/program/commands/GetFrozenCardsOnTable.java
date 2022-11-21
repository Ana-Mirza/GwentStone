package program.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import program.deck.Card;
import program.deck.minion.Minion;
import program.dependencies.Player;
import program.dependencies.Table;

import java.util.ArrayList;

public class GetFrozenCardsOnTable implements Command {
    public GetFrozenCardsOnTable() { }

    /**
     * Method saves frozen card on the table, to be displayed.
     *
     * @param output stores output of action
     * @param player stores current status of players' hands, decks,
     *               heroes, mana available, round, won games, and
     *               total games played; parameter not used for this
     *               action
     * @param table stores current status of the cards on the table
     */
    public final void action(final ArrayNode output, final ArrayList<Player> player,
                             final Table table) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("command", "getFrozenCardsOnTable");

        // Find frozen cards on table
        ArrayList<Card> list = new ArrayList<>();
        for (ArrayList<Card> row : table.getTable()) {
            for (Card card : row) {
                if (((Minion) card).getFrozen() == 1) {
                    list.add(card);
                }
            }
        }
        node.set("output", mapper.valueToTree(list));
        output.add(node);
    }
}
