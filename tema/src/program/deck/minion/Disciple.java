package program.deck.minion;

import fileio.CardInput;
import program.deck.Card;

public class Disciple extends Minion{
    // Constructor
    public Disciple(CardInput input) {
        super(input);
        super.setAttackDamage(0);
    }

    // Card ability
    @Override
    public void ability(Card minion) {
        minion.setHealth(minion.getHealth() + 2);
    }
}
