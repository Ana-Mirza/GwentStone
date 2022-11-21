package program.deck.environment;

import fileio.CardInput;
import program.deck.Card;
import program.deck.minion.Minion;

import java.util.ArrayList;

public class HeartHound extends Environment{
    private int mana;
    private String description;
    private ArrayList<String> colors;
    private String name;
    // Constructor
    public HeartHound(CardInput input) {
        super(input);
    }

    // Card ability
    public void ability(ArrayList<Card> row, ArrayList<Card> row2) {
        Card max = row.get(0);
        int index = 0;
        int i = 0;

        // find card with maximum health
        for (Card card: row) {
            if (card.getHealth() > max.getHealth()) {
                max = card;
                index = i;
            }
            i++;
        }

        // remove card from enemy and place in current row
        row.remove(index);
        row2.add(max);
    }
}
