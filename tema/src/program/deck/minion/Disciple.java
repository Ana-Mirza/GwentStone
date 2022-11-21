package program.deck.minion;

import fileio.CardInput;
import program.deck.Card;

public final class Disciple extends Minion {
    // Constructor
    public Disciple(final CardInput input) {
        super(input);
        super.setAttackDamage(0);
    }

    /**
     * Method adds +2 health to @param minion belonging to current
     * player.
     *
     * @param minion specifies minion on which card ability is used
     */
    @Override
    public void ability(final Card minion) {
        minion.setHealth(minion.getHealth() + 2);
    }
}
