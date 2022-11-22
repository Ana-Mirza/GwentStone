package program.commands;

import com.fasterxml.jackson.databind.node.ArrayNode;
import program.deck.Card;
import program.deck.hero.Hero;
import program.deck.minion.Minion;
import program.dependencies.Player;
import program.dependencies.Table;

import java.util.ArrayList;

public final class EndPlayerTurn implements Command {
    public EndPlayerTurn() { }

    /**
     * Defines the actions to be done at the end of a turn,
     * that is to unfreeze cards and hero, restore attack ability
     * of cards belonging to player that ended his turn, set
     * current player and round parameter. This action has no output.
     *
     * @param output stores output of command; parameter not used for
     *               this action
     * @param player stores current status of players' hands, decks,
     *               heroes, mana available, round, won games, and
     *               total games played
     * @param table stores current status of the cards on the table;
     *              parameter not used for this action
     */
    @Override
    public void action(final ArrayNode output, final ArrayList<Player> player,
                             final Table table) {

        // find rows of cards of player
        ArrayList<ArrayList<Card>> rows = new ArrayList<>();
        if (player.get(0).getPlayerIdx() == 1) {
            rows.add(table.getTable().get(2));
            rows.add(table.getTable().get(3));
        } else {
            rows.add(table.getTable().get(0));
            rows.add(table.getTable().get(1));
        }

        // Unfreeze cards and reset ability attack
        for (ArrayList<Card> row: rows) {
            for (Card card: row) {
                ((Minion) card).setFrozen(0);
                ((Minion) card).setAttacked(0);
            }
        }

        // Unfreeze hero
        int index = (player.get(0).getPlayerIdx() + 1) % 2;
        Hero hero = player.get(index).getHero();
        hero.setAttacked(0);

        // Set active player and round
        player.get(0).setRound(player.get(0).getRound() + 1);
        player.get(0).setPlayerIdx(player.get(0).getPlayerIdx() + 1);
        if (player.get(0).getPlayerIdx() > 2) {
            player.get(0).setPlayerIdx(1);
        }
    }
}
