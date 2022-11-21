package program.dependencies;

import program.deck.Card;

import java.util.ArrayList;

public final class Hand {
    private ArrayList<Card> cards;
    public Hand() {
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
