package program.deck.hero;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fileio.CardInput;
import program.deck.Card;

import java.util.ArrayList;

@JsonIgnoreProperties(value = { "attackDamage" })
public abstract class Hero extends Card {
    @JsonIgnore
    private int attacked;

    // Constructor
    public Hero(final CardInput input) {
        super(input);
        final int heroHealth = 30;
        super.setHealth(heroHealth);
        attacked = 0;
    }

    // Setters and Getters
    public final int getAttacked() {
        return attacked;
    }
    public final void setAttacked(final int attacked) {
        this.attacked = attacked;
    }

    /**
     *
     * @param row specifies row on table on which hero ability
     *            should be unleashed
     */
    public void ability(final ArrayList<Card> row) { }
}
