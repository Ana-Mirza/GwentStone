package program.deck.minion;

import fileio.CardInput;
import program.deck.Card;

public class TheCursedOne extends Minion{
    // Constructor
    public TheCursedOne(CardInput input) {
        super(input);
        super.setAttackDamage(0);
    }

    // Card ability
    @Override
    public void ability(Card minion) {
        int health = minion.getHealth();
        minion.setHealth(minion.getAttackDamage());
        minion.setAttackDamage(health);
    }
}
