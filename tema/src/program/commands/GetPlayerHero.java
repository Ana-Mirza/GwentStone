package program.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import program.dependencies.Player;
import program.dependencies.Table;

import java.util.ArrayList;

public final class GetPlayerHero implements Command {
    private final int playerIdx;
    public GetPlayerHero(final ActionsInput input) {
        this.playerIdx = input.getPlayerIdx();
    }

    /**
     * Method saves hero status of the current player, to be
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
    public void action(final ArrayNode output, final ArrayList<Player> player,
                             final Table table) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("command", "getPlayerHero");
        node.put("playerIdx", playerIdx);
        node.set("output", mapper.valueToTree(player.get((playerIdx + 1) % 2).getHero()));
        output.add(node);
    }
}
