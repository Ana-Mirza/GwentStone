package program.deck.minion;

import fileio.CardInput;
import main.Test;
import program.deck.Card;

public class TheRipper extends Minion{
    // Constructor
    public TheRipper(CardInput input) {
        super(input);
    }

    // Card ability
    @Override
    public void ability(Card minion) {
        minion.setAttackDamage(minion.getAttackDamage() - 2);

        // Check if attack damage is less than 0
        if (minion.getAttackDamage() < 0)
            minion.setAttackDamage(0);
    }
}
