package program.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import program.deck.Card;
import program.deck.environment.Environment;
import program.dependencies.Player;
import program.dependencies.Table;

import java.util.ArrayList;

public class GetEnvironmentCardsInHand implements Command {
    private final int playerIdx;

    public GetEnvironmentCardsInHand(final ActionsInput input) {
        this.playerIdx = input.getPlayerIdx();
    }

    /**
     * Method saves environment cards in the hand of the current
     * player, to be displayed.
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
        node.put("command", "getEnvironmentCardsInHand");
        node.put("playerIdx", playerIdx);

        // Find environment cards in hand
        ArrayList<Card> list = new ArrayList<>();
        for (Card card: player.get((playerIdx + 1) % 2).getHand().getCards()) {
            if (card instanceof Environment) {
                list.add(card);
            }
        }
        node.set("output", mapper.valueToTree(list));
        output.add(node);
    }
}
