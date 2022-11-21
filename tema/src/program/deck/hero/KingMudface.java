package program.deck.hero;

import fileio.CardInput;
import program.deck.Card;
import program.deck.minion.Minion;

import java.util.ArrayList;

public class KingMudface extends Hero{
    // Constructor
    public KingMudface(CardInput input) {
        super(input);
    }

    // Hero ability
    @Override
    public void ability(ArrayList<Card> row) {
        // add +1 health to all cards
        for (Card card: row)
            card.setHealth(card.getHealth() + 1);
    }
}
