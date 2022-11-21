package program.deck.minion;

import fileio.CardInput;
import program.deck.Card;

public class Miraj extends Minion{
    // Constructor
    public Miraj(CardInput input) {
        super(input);
        super.setAttackDamage(input.getAttackDamage());
    }

    // Card ability
    @Override
    public void ability(Card minion) {
        int health = minion.getHealth();
        minion.setHealth(this.getHealth());
        this.setHealth(health);
    }
}
