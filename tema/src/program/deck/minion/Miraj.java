package program.deck.minion;

import fileio.CardInput;
import program.deck.Card;

public final class Miraj extends Minion {
    public Miraj(final CardInput input) {
        super(input);
        super.setAttackDamage(input.getAttackDamage());
    }

    /**
     * Method swaps life of current minion with that of @param
     * minion belonging to opponent player.
     *
     * @param minion specifies minion on which card ability is used
     */
    @Override
    public void ability(final Card minion) {
        int health = minion.getHealth();
        minion.setHealth(this.getHealth());
        this.setHealth(health);
    }
}
