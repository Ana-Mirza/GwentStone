package program.deck.minion;

import fileio.CardInput;
import program.deck.Card;

public final class TheCursedOne extends Minion {
    public TheCursedOne(final CardInput input) {
        super(input);
        super.setAttackDamage(0);
    }

    /**
     * Method swaps health of @param minion with @param minion's
     * attack-damage.
     *
     * @param minion specifies minion on which card ability is used
     */
    @Override
    public void ability(final Card minion) {
        int health = minion.getHealth();
        minion.setHealth(minion.getAttackDamage());
        minion.setAttackDamage(health);
    }
}
