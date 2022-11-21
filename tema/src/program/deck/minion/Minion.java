package program.deck.minion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.CardInput;
import program.deck.Card;

public abstract class Minion extends Card {
    @JsonIgnore
    private int frozen;
    @JsonIgnore
    private int attacked;

    // Constructor
    public Minion(final CardInput input) {
        super(input);
        frozen = 0;
        attacked = 0;
    }

    public final int getFrozen() {
        return frozen;
    }
    public final void setFrozen(final int frozen) {
        this.frozen = frozen;
    }
    public final int getAttacked() {
        return attacked;
    }
    public final void setAttacked(final int attacked) {
        this.attacked = attacked;
    }

    /**
     * Method describes structure of ability action of a minion card
     * to be applied to a valid specified minion.
     *
     * @param minion specifies minion on which card ability is used
     */
    public void ability(final Card minion) { }
}
