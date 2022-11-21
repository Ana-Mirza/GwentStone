package program.deck.environment;

import fileio.CardInput;
import program.deck.Card;
import program.deck.minion.Minion;

import java.util.ArrayList;

public class Firestorm extends Environment{
    // Constructor
    public Firestorm(CardInput input) {
        super(input);
    }

    // Card ability
    @Override
    public void ability(ArrayList<Card> row) {
        int size = row.size();

        // Apply -1 health to all cards on row
        for (int i = 0; i < row.size(); i++) {
            row.get(i).setHealth(row.get(i).getHealth() - 1);
            // Delete card from table if health is 0
            if (row.get(i).getHealth() == 0) {
                row.remove(i);
                i--;
            }
        }
    }
}
