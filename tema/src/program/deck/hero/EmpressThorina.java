package program.deck.hero;

import fileio.CardInput;
import program.deck.Card;
import program.deck.minion.Minion;

import java.util.ArrayList;

public class EmpressThorina extends Hero{
    // Constructor
    public EmpressThorina(CardInput input) {
        super(input);
    }

    // Hero ability
    @Override
    public void ability(ArrayList<Card> row) {
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

        // Destroy card
        row.remove(index);
    }
}
