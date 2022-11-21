package program.deck.hero;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fileio.CardInput;
import program.deck.Card;

import java.util.ArrayList;

@JsonIgnoreProperties(value = { "attackDamage" })
public abstract class Hero extends Card{
    @JsonIgnore
    private int attacked;

    // Constructor
    public Hero(CardInput input) {
        super(input);
        super.setHealth(30);
        attacked = 0;
    }

    // Setters and Getters
    public int getAttacked() {
        return attacked;
    }
    public void setAttacked(int attacked) {
        this.attacked = attacked;
    }
    public void ability(ArrayList<Card> row) {};
}
