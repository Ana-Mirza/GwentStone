package program.deck.environment;

import fileio.CardInput;
import program.deck.Card;
import program.deck.minion.Minion;

import java.util.ArrayList;

public final class Winterfell extends Environment {
    // Constructor
    public Winterfell(final CardInput input) {
        super(input);
    }

    /**
     * Method freezes all cards on @param row
     *
     * @param row specifies row on table on which ability
     *            of environment card to be unleashed
     */
    @Override
    public void ability(final ArrayList<Card> row) {
        for (Card card: row) {
            ((Minion) card).setFrozen(1);
        }
    }
}
