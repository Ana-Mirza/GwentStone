package program.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import program.dependencies.Player;
import program.dependencies.Table;

import java.util.ArrayList;

public class GetTotalGamesPlayed implements Command {
    public GetTotalGamesPlayed() { }

    /**
     * Method saves total number of games played by players, to be
     * displayed.
     *
     * @param output stores output of action
     * @param player stores current status of players' hands, decks,
     *               heroes, mana available, round, won games, and
     *               total games played
     * @param table stores current status of the cards on the table;
     *              parameter not used for this action
     */
    @Override
    public final void action(final ArrayNode output, final ArrayList<Player> player,
                             final Table table) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("command", "getTotalGamesPlayed");
        node.put("output", player.get(0).getTotalGames());
        output.add(node);
    }
}
