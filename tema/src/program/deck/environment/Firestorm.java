package program.deck.environment;

import fileio.CardInput;
import program.deck.Card;

import java.util.ArrayList;

public final class Firestorm extends Environment {
    // Constructor
    public Firestorm(final CardInput input) {
        super(input);
    }

    /**
     * Method applies -1 health to all cards on row.
     *
     * @param row specifies row on table on which ability
     *            of environment card to be unleashed
     */
    @Override
    public void ability(final ArrayList<Card> row) {
        for (int i = 0; i < row.size(); i++) {
            row.get(i).setHealth(row.get(i).getHealth() - 1);
            // Delete card from table if health is 0
            if (row.get(i).getHealth() == 0) {
                row.remove(i);
                i--;
            }
        }
    }
}
