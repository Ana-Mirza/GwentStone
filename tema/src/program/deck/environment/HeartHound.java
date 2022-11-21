package program.deck.environment;

import fileio.CardInput;
import program.deck.Card;

import java.util.ArrayList;

public final class HeartHound extends Environment {
    public HeartHound(final CardInput input) {
        super(input);
    }

    /**
     * Method finds minion with the greatest health on @param row
     * and places it on mirror row of current player, if it
     * has space.
     *
     * @param row row of opponent player from which card is to
     *            be stolen
     * @param row2 row on which card stolen is to be placed
     */
    public void ability(final ArrayList<Card> row, final ArrayList<Card> row2) {
        Card max = row.get(0);
        int index = 0;
        int i = 0;

        // find card with maximum health
        for (Card card: row) {
            if (card.getHealth() > max.getHealth()) {
                max = card;
                index = i;
            }
            i++;
        }

        // remove card from enemy and place in current row
        row.remove(index);
        row2.add(max);
    }
}
