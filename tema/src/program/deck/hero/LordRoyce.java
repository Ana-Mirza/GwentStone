package program.deck.hero;

import fileio.CardInput;
import program.deck.Card;
import program.deck.minion.Minion;

import java.util.ArrayList;

public class LordRoyce extends Hero{
    // Constructor
    public LordRoyce(CardInput input) {
        super(input);
    }

    // Hero ability
    @Override
    public void ability(ArrayList<Card> row) {
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
