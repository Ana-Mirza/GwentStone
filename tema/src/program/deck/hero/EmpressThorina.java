package program.deck.hero;

import fileio.CardInput;
import program.deck.Card;

import java.util.ArrayList;

public final class EmpressThorina extends Hero {
    // Constructor
    public EmpressThorina(final CardInput input) {
        super(input);
    }

    /**
     * Method finds card with the greatest health on @param row
     * and kills it.
     *
     * @param row specifies row on table on which hero ability
     *            should be unleashed
     */
    @Override
    public void ability(final ArrayList<Card> row) {
        Card max = row.get(0);
        int index = 0;
        int i = 0;

        // Find card with maximum health on row
        for (Card card: row) {
            if (card.getHealth() > max.getHealth()) {
                max = card;
                index = i;
            }
            i++;
        }

        // Destroy card by removing it from table row
        row.remove(index);
    }
}
