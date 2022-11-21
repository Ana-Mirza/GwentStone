package program.deck.hero;

import fileio.CardInput;
import program.deck.Card;

import java.util.ArrayList;

public final class KingMudface extends Hero {
    // Constructor
    public KingMudface(final CardInput input) {
        super(input);
    }

    /**
     * Method adds +1 health to all cards on @param row
     *
     * @param row specifies row on table on which hero ability
     *            should be unleashed
     */
    @Override
    public void ability(final ArrayList<Card> row) {
        // add +1 health to all cards
        for (Card card: row) {
            card.setHealth(card.getHealth() + 1);
        }
    }
}
