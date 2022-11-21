package program.deck;

import fileio.CardInput;
import fileio.DecksInput;

import java.util.ArrayList;
import java.util.SimpleTimeZone;

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
    public Card(CardInput input) {
        mana = input.getMana();
        attackDamage = input.getAttackDamage();
        health = input.getHealth();
        description = input.getDescription();
        colors = input.getColors();
        name = input.getName();
    }
    public Card(Card card) {
        mana = card.getMana();
        attackDamage = card.getAttackDamage();
        health = card.getHealth();
        description = card.getDescription();
        colors = card.getColors();
        name = card.getName();
    }

    // Setters and Getters
    public int getMana() {
        return mana;
    }
    public void setMana(final int mana) {
        this.mana = mana;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(final String description) {
        this.description = description;
    }
    public ArrayList<String> getColors() {
        return colors;
    }
    public void setColors(final ArrayList<String> colors) {
        this.colors = colors;
    }
    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public int getAttackDamage() {
        return attackDamage;
    }
    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    // Ability method
    public void ability() {};
}
