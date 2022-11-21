package program.deck;

import fileio.DecksInput;

import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;

    // Constructor
    public Deck() {
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
