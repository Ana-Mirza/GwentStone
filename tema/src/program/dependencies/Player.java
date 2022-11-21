package program.dependencies;

import program.deck.Deck;
import program.deck.hero.Hero;

public final class Player {
    private Deck deck;
    private Hand hand;
    private Hero hero;
    private int mana;
    private int round;
    private int wonGames = 0;
    private int totalGames;
    private int playerIdx;

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(final int totalGames) {
        this.totalGames = totalGames;
    }

    // Constructor
    public Player() {
        deck = new Deck();
        hand = new Hand();
    }

    // Setters and Getters
    public int getWonGames() {
        return wonGames;
    }
    public void setWonGames(final int wonGames) {
        this.wonGames = wonGames;
    }
    public int getMana() {
        return mana;
    }
    public void setMana(final int mana) {
        this.mana = mana;
    }
    public Hero getHero() {
        return hero;
    }
    public void setHero(final Hero hero) {
        this.hero = hero;
    }
    public Hand getHand() {
        return hand;
    }
    public void setHand(final Hand hand) {
        this.hand = hand;
    }
    public Deck getDeck() {
        return deck;
    }
    public void setDeck(final Deck deck) {
        this.deck = deck;
    }
    public int getRound() {
        return round;
    }
    public void setRound(final int round) {
        this.round = round;
    }
    public int getPlayerIdx() {
        return playerIdx;
    }
    public void setPlayerIdx(final int playerIdx) {
        this.playerIdx = playerIdx;
    }
}
