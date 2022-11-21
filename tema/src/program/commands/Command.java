package program.commands;

import com.fasterxml.jackson.databind.node.ArrayNode;
import program.dependencies.Player;
import program.dependencies.Table;

import java.util.ArrayList;

/**
 * Defines the structure of a command and declares
 * the action method to be defined by all classes
 * implementing the command interface.
 */
public interface Command {
    /**
     * Method to be implemented by specific commands.
     * It provides current status of players and of the
     * table, and stores output of action to be displayed.
     *
     * @param output stores output of action
     * @param player stores current status of players' hands, decks,
     *               heroes, mana available, round, won games, and
     *               total games played
     * @param table stores current status of the cards on the table
     */
    void action(ArrayNode output, ArrayList<Player> player, Table table);
}
