package program.deck.hero;

import fileio.CardInput;
import program.deck.Card;
import program.deck.minion.Minion;

import java.util.ArrayList;

public class GeneralKocioraw extends Hero{
    // Constructor
    public GeneralKocioraw(CardInput input) {
        super(input);
    }

    // Hero ability

    @Override
    public void ability(ArrayList<Card> row) {
        // Add +1 attack to all cards on row
        for (Card card: row)
            card.setAttackDamage(card.getAttackDamage() + 1);
    }
}
