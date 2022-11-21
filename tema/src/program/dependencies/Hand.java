package program.dependencies;

import program.deck.Card;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;

    // Constructor
    public Hand() {
        cards = new ArrayList<>();
    }

    // Setters and Getters
    public ArrayList<Card> getCards() {
        return cards;
    }
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
}
