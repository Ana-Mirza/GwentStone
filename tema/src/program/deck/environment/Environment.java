package program.deck.environment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fileio.ActionsInput;
import fileio.CardInput;
import program.deck.Card;

import java.util.ArrayList;

@JsonIgnoreProperties(value = { "attackDamage" , "health"})
public abstract class Environment extends Card {
    // Constructors
    public Environment(CardInput input) {
        super(input);
    }
    public void ability(ArrayList<Card> row) {};
}
