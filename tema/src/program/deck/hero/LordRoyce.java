package program.deck.hero;

import fileio.CardInput;
import program.deck.Card;
import program.deck.minion.Minion;

import java.util.ArrayList;

public final class LordRoyce extends Hero {
    // Constructor
    public LordRoyce(final CardInput input) {
        super(input);
    }

    /**
     * Method finds card with greatest attack-damage on @param row
     * and freezes it.
     *
     * @param row specifies row on table on which hero ability
     *            should be unleashed
     */
    @Override
    public void ability(final ArrayList<Card> row) {
        Card max = row.get(0);
        int index = 0;
        int i = 0;

        // Find card with maximum attack on row
        for (Card card: row) {
            if (card.getAttackDamage() > max.getAttackDamage()) {
                max = card;
                index = i;
            }
            i++;
        }

        // Freeze card
        ((Minion) row.get(index)).setFrozen(1);
    }
}
