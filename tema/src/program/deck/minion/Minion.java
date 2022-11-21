package program.deck.minion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.CardInput;
import program.deck.Card;

import java.util.ArrayList;

public abstract class Minion extends Card {
//    @JsonIgnore
//    private int tank;
    @JsonIgnore
    private int frozen;
    @JsonIgnore
    private int attacked;

    // Constructor
    public Minion(CardInput input) {
        super(input);
        frozen = 0;
        attacked = 0;
    }

    public int getFrozen() {
        return frozen;
    }
    public void setFrozen(int frozen) {
        this.frozen = frozen;
    }
    public int getAttacked() {
        return attacked;
    }
    public void setAttacked(int attacked) {
        this.attacked = attacked;
    }
    public void ability(Card minion) {};
}
