package program.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import program.dependencies.Player;
import program.dependencies.Table;

import java.util.ArrayList;

public class GetPlayerDeck implements Command {
    private final int playerIdx;
    public GetPlayerDeck(final ActionsInput input) {
        this.playerIdx = input.getPlayerIdx();
    }

    /**
     * Method saves current player's deck to be displayed.
     *
     * @param output stores output of action
     * @param player stores current status of players' hands, decks,
     *               heroes, mana available, round, won games, and
     *               total games played
     * @param table stores current status of the cards on the table;
     *              parameter not used for this action
     */
    public final void action(final ArrayNode output, final ArrayList<Player> player,
                             final Table table) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("command", "getPlayerDeck");
        node.put("playerIdx", playerIdx);
        node.set("output", mapper.valueToTree(player.get(
                (playerIdx + 1) % 2).getDeck().getCards()));
        output.add(node);
    }
}
