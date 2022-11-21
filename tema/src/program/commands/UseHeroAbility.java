package program.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import program.deck.Card;
import program.deck.hero.Hero;
import program.deck.hero.LordRoyce;
import program.deck.hero.EmpressThorina;
import program.deck.hero.KingMudface;
import program.deck.hero.GeneralKocioraw;
import program.dependencies.Player;
import program.dependencies.Table;

import java.util.ArrayList;

public final class UseHeroAbility implements Command {
    private final int affectedRow;

    // Constructor
    public UseHeroAbility(final ActionsInput input) {
        affectedRow = input.getAffectedRow();
    }

    /**
     * Method applies current player's hero ability on chosen row
     * and saves any error in output.
     *
     * @param output stores output of action
     * @param player stores current status of players' hands, decks,
     *               heroes, mana available, round, won games, and
     *               total games played
     * @param table stores current status of the cards on the table
     */
    public void action(final ArrayNode output, final ArrayList<Player> player,
                       final Table table) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("command", "useHeroAbility");
        node.put("affectedRow", affectedRow);

        // Check errors
        if (manaError(node, player) || heroUsedError(node, player)
                || enemyRowError(node, player) || alliedRowError(node, player)) {
            output.add(node);
            return;
        }

        // Use hero ability
        int index = (player.get(0).getPlayerIdx() + 1) % 2;
        Hero hero = player.get(index).getHero();
        hero.setAttacked(1);

        ArrayList<Card> row = table.getTable().get(affectedRow);
        hero.ability(row);

        // Update player's mana
        int currentMana = player.get(index).getMana();
        player.get(index).setMana(currentMana - hero.getMana());
    }

    // Check if player has enough mana to use hero ability
    private boolean manaError(final ObjectNode node, final ArrayList<Player> player) {
        int index = (player.get(0).getPlayerIdx() + 1) % 2;
        int playerMana = player.get(index).getMana();
        Hero hero = player.get(index).getHero();
        int heroMana = hero.getMana();

        if (heroMana > playerMana) {
            node.put("error", "Not enough mana to use hero's ability.");
            return true;
        }
        return false;
    }

    // Check if hero already attacked this turn
    private boolean heroUsedError(final ObjectNode node, final ArrayList<Player> player) {
        int index = (player.get(0).getPlayerIdx() + 1) % 2;
        Hero hero = player.get(index).getHero();
        if (hero.getAttacked() == 1) {
            node.put("error", "Hero has already attacked this turn.");
            return true;
        }
        return false;
    }

    // Check if hero is Lord Royce or Empress Thorina and selected row belongs to enemy
    private boolean enemyRowError(final ObjectNode node, final ArrayList<Player> player) {
        int playerIdx = player.get(0).getPlayerIdx();
        Hero hero = player.get((playerIdx + 1) % 2).getHero();
        if ((hero instanceof LordRoyce || hero instanceof EmpressThorina)
                && playerIdx == 1 && (affectedRow == 2 || affectedRow == 3)) {
            node.put("error", "Selected row does not belong to the enemy.");
            return true;
        } else if ((hero instanceof LordRoyce || hero instanceof EmpressThorina)
                && playerIdx == 2 && (affectedRow == 0 || affectedRow == 1)) {
            node.put("error", "Selected row does not belong to the enemy.");
            return true;
        }
        return false;
    }

    // Check if hero is General Kocioraw or King Mudface and selected row belongs to enemy
    private boolean alliedRowError(final ObjectNode node, final ArrayList<Player> player) {
        int playerIdx = player.get(0).getPlayerIdx();
        Hero hero = player.get((playerIdx + 1) % 2).getHero();
        if ((hero instanceof GeneralKocioraw || hero instanceof KingMudface)
                && playerIdx == 1 && (affectedRow == 0 || affectedRow == 1)) {
            node.put("error", "Selected row does not belong to the current player.");
            return true;
        } else if ((hero instanceof GeneralKocioraw || hero instanceof KingMudface)
                && playerIdx == 2 && (affectedRow == 2 || affectedRow == 3)) {
            node.put("error", "Selected row does not belong to the current player.");
            return true;
        }
        return false;
    }
}
