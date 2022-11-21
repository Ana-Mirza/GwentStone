package program.deck.hero;

import fileio.CardInput;
import program.deck.Card;

import java.util.ArrayList;

public final class GeneralKocioraw extends Hero {
    // Constructor
    public GeneralKocioraw(final CardInput input) {
        super(input);
    }

    /**
     * Method adds +1 attack-damage to all cards on @param row
     *
     * @param row specifies row on table on which hero ability
     *            should be unleashed
     */
    @Override
    public void ability(final ArrayList<Card> row) {
        // Add +1 attack to all cards on row
        for (Card card: row) {
            card.setAttackDamage(card.getAttackDamage() + 1);
        }
    }
}
