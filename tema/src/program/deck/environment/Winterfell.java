package program.deck.environment;

import fileio.CardInput;
import program.deck.Card;
import program.deck.minion.Minion;

import java.util.ArrayList;

public class Winterfell extends Environment{
    // Constructor
    public Winterfell(CardInput input) {
        super(input);
    }

    // Card ability
    @Override
    public void ability(ArrayList<Card> row) {
        for (Card card: row)
            ((Minion) card).setFrozen(1);
    }
}
