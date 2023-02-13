package program;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.Input;
import program.commands.Command;
import program.dependencies.Player;
import program.dependencies.Table;

import java.util.ArrayList;

public final class Program {

    // Singleton Pattern
    private static Program instance = null;
    private Program() { }

    /**
     * Instantiates program class using Singleton Pattern
     *
     * @return returns unique instance of program class
     */
    public static Program getInstance() {
        if (instance == null) {
            instance = new Program();
        }
        return instance;
    }

    /**
     * Entry point of the program. It runs the games specified in input
     * by uploading given information about players, their decks and
     * heroes, and runs games and commands given. It returns the output
     * of specific commands in output.
     *
     * @param inputData stores input data from input file
     * @param output stores output of game commands
     */
    public void start(final Input inputData, final ArrayNode output) {
        // Create new players
        ArrayList<Player> player = new ArrayList<>();
        player.add(new Player());
        player.add(new Player());

        // Run games
        for (int i = 0; i < inputData.getGames().size(); i++) {
            // Set new table and player attributes at beginning of game
            Table table = new Table();
            Upload upload = new Upload();
            // Set round index and mana
            player.get(0).setRound(2);
            int mana = 1;

            // Update number of games played
            player.get(0).setTotalGames(i + 1);
            player.get(1).setTotalGames(i + 1);

            // Upload players
            upload.uploadPlayers(inputData, player, i);
            player.get(0).setPlayerIdx(
                    inputData.getGames().get(i).getStartGame().getStartingPlayer());
            // shuffle decks
            upload.shuffleDecks(player,
                    inputData.getGames().get(i).getStartGame().getShuffleSeed());

            // Execute actions
            for (ActionsInput command: inputData.getGames().get(i).getActions()) {
                // Check for new round
                if (player.get(0).getRound() == 2) {
                    player.get(0).setRound(0);
                    upload.newRound(player, mana);
                    // Increase mana for next round

                    final int maxMana = 10;
                    if (mana < maxMana) {
                        mana++;
                    }
                }
                // Upload command
                Command newCommand = upload.uploadCommand(command);
                newCommand.action(output, player, table);
            }
        }
    }
}
