package program;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.CardInput;
import fileio.Input;
import program.commands.Command;
import program.deck.Card;
import program.deck.environment.Firestorm;
import program.deck.environment.HeartHound;
import program.deck.environment.Winterfell;
import program.deck.hero.*;
import program.deck.minion.*;
import program.dependencies.Hand;
import program.dependencies.Player;
import program.dependencies.Table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Program {
    private ArrayList<Player> player;
    private Table table;

    // Singleton Pattern
    private static Program instance = null;
    private Program() {}
    public static Program getInstance() {
        if (instance == null) {
            instance = new Program();
        }
        return instance;
    }

    // Beginning of the program
    public ArrayNode start(Input inputData, ArrayNode output) {
        // Create new players
        player = new ArrayList<>();
        player.add(new Player());
        player.add(new Player());

        // Run games
        for (int i = 0; i < inputData.getGames().size(); i++) {
            // Set new table and player attributes at beginning of game
            table = new Table();
            Upload upload = new Upload();
            // Set round index and mana
            player.get(0).setRound(2);
            int mana = 1;

            // Update number of games played
            player.get(0).setTotalGames(i + 1);
            player.get(1).setTotalGames(i + 1);

            // Upload players
            upload.uploadPlayers(inputData, player, i);
            player.get(0).setPlayerIdx(inputData.getGames().get(i).getStartGame().getStartingPlayer());
            // shuffle decks
            upload.shuffleDecks(player, inputData.getGames().get(i).getStartGame().getShuffleSeed());

            // Execute actions
            for (ActionsInput command: inputData.getGames().get(i).getActions()) {
                // Check for new round
                if (player.get(0).getRound() == 2) {
                    player.get(0).setRound(0);
                    upload.newRound(player, mana);
                    // Increase mana for next round
                    if (mana < 10)
                        mana++;
                }
                // Upload command
                Command newCommand = upload.uploadCommand(command);
                newCommand.action(output, player, table);
            }
        }
        return output;
    }
}
