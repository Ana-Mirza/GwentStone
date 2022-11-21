package program.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import program.dependencies.Player;
import program.dependencies.Table;

import java.util.ArrayList;

public class GetPlayerTwoWins implements Command {
    public GetPlayerTwoWins() { }

    /**
     * Method saves second player's number of won games, to be
     * displayed.
     *
     * @param output stores output of action
     * @param player stores current status of players' hands, decks,
     *               heroes, mana available, round, turn, won games,
     *               and total games played
     * @param table stores current status of the cards on the table;
     *              parameter not used for thsi action
     */
    public final void action(final ArrayNode output, final ArrayList<Player> player,
                       final Table table) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("command", "getPlayerTwoWins");
        node.put("output", player.get(1).getWonGames());
        output.add(node);
    }
}
