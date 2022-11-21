package program.deck;

import java.util.ArrayList;

public final class Deck {
    private ArrayList<Card> cards;

    // Constructor
    public Deck() {
        cards = new ArrayList<>();
    }

    // Setters and Getters
    public ArrayList<Card> getCards() {
        return cards;
    }
    public void setCards(final ArrayList<Card> cards) {
        this.cards = cards;
    }
}
