package program.deck;

import fileio.CardInput;

import java.util.ArrayList;

public abstract class Card {
    private int mana;
    private int attackDamage;
    private int health;
    private String description;
    private ArrayList<String> colors;
    private String name;

    // Constructors
    public Card() {
        colors = new ArrayList<>();
    }
    public Card(final CardInput input) {
        mana = input.getMana();
        attackDamage = input.getAttackDamage();
        health = input.getHealth();
        description = input.getDescription();
        colors = input.getColors();
        name = input.getName();
    }
    public Card(final Card card) {
        mana = card.getMana();
        attackDamage = card.getAttackDamage();
        health = card.getHealth();
        description = card.getDescription();
        colors = card.getColors();
        name = card.getName();
    }

    // Setters and Getters
    public final int getMana() {
        return mana;
    }
    public final void setMana(final int mana) {
        this.mana = mana;
    }
    public final String getDescription() {
        return description;
    }
    public final void setDescription(final String description) {
        this.description = description;
    }
    public final ArrayList<String> getColors() {
        return colors;
    }
    public final void setColors(final ArrayList<String> colors) {
        this.colors = colors;
    }
    public final String getName() {
        return name;
    }
    public final void setName(final String name) {
        this.name = name;
    }
    public final int getAttackDamage() {
        return attackDamage;
    }
    public final void setAttackDamage(final int attackDamage) {
        this.attackDamage = attackDamage;
    }
    public final int getHealth() {
        return health;
    }
    public final void setHealth(final int health) {
        this.health = health;
    }

    /**
     * Method that implements a specific card ability.
     * To be defined by classes extending this class.
     */
    public void ability() { }
}
