package program;

import fileio.ActionsInput;
import fileio.CardInput;
import fileio.Input;
import program.commands.*;
import program.deck.Deck;
import program.deck.environment.Firestorm;
import program.deck.environment.HeartHound;
import program.deck.environment.Winterfell;
import program.deck.hero.*;
import program.deck.minion.*;
import program.dependencies.Hand;
import program.dependencies.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Upload {
    protected void uploadPlayers(Input input, ArrayList<Player> players, int index) {
        // Set players' hand and mana
        for (Player player: players) {
            player.setDeck(new Deck());
            player.setHand(new Hand());
            player.setMana(0);
        }

        // Set players' deck
        int idx = input.getGames().get(index).getStartGame().getPlayerOneDeckIdx();
        uploadCards(players.get(0), input.getPlayerOneDecks().getDecks().get(idx));
        idx = input.getGames().get(index).getStartGame().getPlayerTwoDeckIdx();
        uploadCards(players.get(1), input.getPlayerTwoDecks().getDecks().get(idx));

        // Set players' hero
        CardInput hero = input.getGames().get(index).getStartGame().getPlayerOneHero();
        uploadHero(hero, players.get(0));
        hero = input.getGames().get(index).getStartGame().getPlayerTwoHero();
        uploadHero(hero, players.get(1));

    }
    protected void uploadCards(Player player, ArrayList<CardInput> deck) {
        for (CardInput card: deck) {
            if (card.getName().equals("Sentinel"))
                player.getDeck().getCards().add(new Sentinel(card));
            else if (card.getName().equals("Berserker"))
                player.getDeck().getCards().add(new Berserker(card));
            else if (card.getName().equals("Goliath"))
                player.getDeck().getCards().add(new Goliath(card));
            else if (card.getName().equals("Warden"))
                player.getDeck().getCards().add(new Warden(card));
            else if (card.getName().equals("Miraj"))
                player.getDeck().getCards().add(new Miraj(card));
            else if (card.getName().equals("The Ripper"))
                player.getDeck().getCards().add(new TheRipper(card));
            else if (card.getName().equals("Disciple"))
                player.getDeck().getCards().add(new Disciple(card));
            else if (card.getName().equals("The Cursed One"))
                player.getDeck().getCards().add(new TheCursedOne(card));
            else if (card.getName().equals("Firestorm"))
                player.getDeck().getCards().add(new Firestorm(card));
            else if (card.getName().equals("Winterfell"))
                player.getDeck().getCards().add(new Winterfell(card));
            else if (card.getName().equals("Heart Hound"))
                player.getDeck().getCards().add(new HeartHound(card));
        }
    }
    protected void uploadHero(CardInput hero, Player player) {
        // create instance for hero of player
        if (hero.getName().equals("Lord Royce"))
            player.setHero(new LordRoyce(hero));
        else if (hero.getName().equals("Empress Thorina"))
            player.setHero(new EmpressThorina(hero));
        else if (hero.getName().equals("King Mudface"))
            player.setHero(new KingMudface(hero));
        else
            player.setHero(new GeneralKocioraw(hero));
    }
    protected Command uploadCommand(ActionsInput command) {
        Command myCommand;
        if (command.getCommand().equals("getCardsInHand"))
            myCommand = new GetCardsInHand(command);
        else if(command.getCommand().equals("getPlayerDeck"))
            myCommand = new GetPlayerDeck(command);
        else if(command.getCommand().equals("getCardsOnTable"))
            myCommand = new GetCardsOnTable(command);
        else if(command.getCommand().equals("getPlayerTurn"))
            myCommand = new GetPlayerTurn(command);
        else if(command.getCommand().equals("getPlayerHero"))
            myCommand = new GetPlayerHero(command);
        else if(command.getCommand().equals("getCardAtPosition"))
            myCommand = new GetCardAtPosition(command);
        else if(command.getCommand().equals("getPlayerMana"))
            myCommand = new GetPlayerMana(command);
        else if(command.getCommand().equals("getEnvironmentCardsInHand"))
            myCommand = new GetEnvironmentCardsInHand(command);
        else if(command.getCommand().equals("getFrozenCardsOnTable"))
            myCommand = new GetFrozenCardsOnTable(command);
        else if(command.getCommand().equals("getTotalGamesPlayed"))
            myCommand = new GetTotalGamesPlayed(command);
        else if(command.getCommand().equals("getPlayerOneWins"))
            myCommand = new GetPlayerOneWins(command);
        else if(command.getCommand().equals("getPlayerTwoWins"))
            myCommand = new GetPlayerTwoWins(command);
        else if(command.getCommand().equals("placeCard"))
            myCommand = new PlaceCard(command);
        else if (command.getCommand().equals("cardUsesAttack"))
            myCommand = new CardUsesAttack(command);
        else if (command.getCommand().equals("useEnvironmentCard"))
            myCommand = new UseEnvironmentCard(command);
        else if (command.getCommand().equals("cardUsesAbility"))
            myCommand = new CardUsesAbility(command);
        else if (command.getCommand().equals("useAttackHero"))
            myCommand = new UseAttackHero(command);
        else if (command.getCommand().equals("useHeroAbility"))
            myCommand = new UseHeroAbility(command);
        else
            myCommand = new EndPlayerTurn(command);
        return myCommand;
    }
    protected void newRound(ArrayList<Player> player, int mana) {
        // Add mana to players
        player.get(0).setMana(player.get(0).getMana() + mana);
        player.get(1).setMana(player.get(1).getMana() + mana);

        // Add card to players' hand if cards available in deck
        if (player.get(0).getDeck().getCards().size() > 0) {
            player.get(0).getHand().getCards().add(player.get(0).getDeck().getCards().get(0));
            player.get(0).getDeck().getCards().remove(0);
        }
        if (player.get(1).getDeck().getCards().size() > 0) {
            player.get(1).getHand().getCards().add(player.get(1).getDeck().getCards().get(0));
            player.get(1).getDeck().getCards().remove(0);
        }
    }
    protected void shuffleDecks(ArrayList<Player> player, int seed) {
        Collections.shuffle(player.get(0).getDeck().getCards(), new Random(seed));
        Collections.shuffle(player.get(1).getDeck().getCards(), new Random(seed));
    }
}
