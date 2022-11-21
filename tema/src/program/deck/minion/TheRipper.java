package program.deck.minion;

import fileio.CardInput;
import program.deck.Card;

public final class TheRipper extends Minion {
    public TheRipper(final CardInput input) {
        super(input);
    }

    /**
     * Method applies -2 attack-damage to @param minion belonging
     * to opponent player.
     *
     * @param minion specifies minion on which card ability is used
     */
    @Override
    public void ability(final Card minion) {
        minion.setAttackDamage(minion.getAttackDamage() - 2);

        // Check if attack damage is less than 0
        if (minion.getAttackDamage() < 0) {
            minion.setAttackDamage(0);
        }
    }
}
