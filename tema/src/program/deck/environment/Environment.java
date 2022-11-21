package program.deck.environment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fileio.CardInput;
import program.deck.Card;

import java.util.ArrayList;

@JsonIgnoreProperties(value = { "attackDamage", "health"})
public abstract class Environment extends Card {
    // Constructors
    public Environment(final CardInput input) {
        super(input);
    }

    /**
     * Method that sets the structure of ability method
     * for environment cards.
     *
     * @param row specifies row on table on which ability
     *            of environment card to be unleashed
     */
    public void ability(final ArrayList<Card> row) { }
}
