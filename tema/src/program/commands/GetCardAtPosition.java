package program.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import program.dependencies.Player;
import program.dependencies.Table;

import java.util.ArrayList;

public class GetCardAtPosition implements Command {
    private final int x;
    private final int y;

    public GetCardAtPosition(final ActionsInput input) {
        this.x = input.getX();
        this.y = input.getY();
    }

    /**
     * Method saves card with given coordinates on table
     * in output, to be displayed, if coordinates are valid.
     *
     * @param output stores output of action
     * @param player stores current status of players' hands, decks,
     *               heroes, mana available, round, won games, and
     *               total games played; parameter not used for this
     *               action
     * @param table stores current status of the cards on the table
     */
    @Override
    public final void action(final ArrayNode output, final ArrayList<Player> player,
                             final Table table) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("command", "getCardAtPosition");
        node.put("x", x);
        node.put("y", y);

        // Check for error
        if (getError(table)) {
            node.put("output", "No card available at that position.");
        } else {
            node.set("output", mapper.valueToTree(table.getTable().get(x).get(y)));
        }
        output.add(node);
    }
    private boolean getError(final Table table) {
        if (y < 0 || x > 3) {
            return true;
        }
        return y >= table.getTable().get(x).size();
    }
}
