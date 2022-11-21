package program;

import fileio.ActionsInput;
import fileio.CardInput;
import fileio.Input;
import program.commands.Command;
import program.commands.GetCardsInHand;
import program.commands.GetPlayerOneWins;
import program.commands.GetPlayerTwoWins;
import program.commands.GetTotalGamesPlayed;
import program.commands.GetPlayerTurn;
import program.commands.GetCardsOnTable;
import program.commands.GetFrozenCardsOnTable;
import program.commands.GetEnvironmentCardsInHand;
import program.commands.GetCardAtPosition;
import program.commands.PlaceCard;
import program.commands.CardUsesAttack;
import program.commands.UseEnvironmentCard;
import program.commands.GetPlayerDeck;
import program.commands.GetPlayerHero;
import program.commands.GetPlayerMana;
import program.commands.CardUsesAbility;
import program.commands.UseAttackHero;
import program.commands.UseHeroAbility;
import program.commands.EndPlayerTurn;
import program.deck.Deck;
import program.deck.environment.Firestorm;
import program.deck.environment.HeartHound;
import program.deck.environment.Winterfell;
import program.deck.hero.LordRoyce;
import program.deck.hero.EmpressThorina;
import program.deck.hero.KingMudface;
import program.deck.hero.GeneralKocioraw;
import program.deck.minion.Berserker;
import program.deck.minion.Goliath;
import program.deck.minion.Sentinel;
import program.deck.minion.Warden;
import program.deck.minion.Miraj;
import program.deck.minion.TheRipper;
import program.deck.minion.Disciple;
import program.deck.minion.TheCursedOne;
import program.dependencies.Hand;
import program.dependencies.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public final class Upload {
    protected void uploadPlayers(final Input input, final ArrayList<Player> players,
                                 final int index) {
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
    protected void uploadCards(final Player player, final ArrayList<CardInput> deck) {
        for (CardInput card: deck) {
            switch (card.getName()) {
                case "Sentinel" -> player.getDeck().getCards().add(new Sentinel(card));
                case "Berserker" -> player.getDeck().getCards().add(new Berserker(card));
                case "Goliath" -> player.getDeck().getCards().add(new Goliath(card));
                case "Warden" -> player.getDeck().getCards().add(new Warden(card));
                case "Miraj" -> player.getDeck().getCards().add(new Miraj(card));
                case "The Ripper" -> player.getDeck().getCards().add(new TheRipper(card));
                case "Disciple" -> player.getDeck().getCards().add(new Disciple(card));
                case "The Cursed One" -> player.getDeck().getCards().add(new TheCursedOne(card));
                case "Firestorm" -> player.getDeck().getCards().add(new Firestorm(card));
                case "Winterfell" -> player.getDeck().getCards().add(new Winterfell(card));
                default -> player.getDeck().getCards().add(new HeartHound(card));
            }
        }
    }
    protected void uploadHero(final CardInput hero, final Player player) {
        // create instance for hero of player
        switch (hero.getName()) {
            case "Lord Royce" -> player.setHero(new LordRoyce(hero));
            case "Empress Thorina" -> player.setHero(new EmpressThorina(hero));
            case "King Mudface" -> player.setHero(new KingMudface(hero));
            default -> player.setHero(new GeneralKocioraw(hero));
        }
    }
    protected Command uploadCommand(final ActionsInput command) {
        return switch (command.getCommand()) {
            case "getCardsInHand" -> new GetCardsInHand(command);
            case "getPlayerDeck" -> new GetPlayerDeck(command);
            case "getCardsOnTable" -> new GetCardsOnTable();
            case "getPlayerTurn" -> new GetPlayerTurn();
            case "getPlayerHero" -> new GetPlayerHero(command);
            case "getCardAtPosition" -> new GetCardAtPosition(command);
            case "getPlayerMana" -> new GetPlayerMana(command);
            case "getEnvironmentCardsInHand" -> new GetEnvironmentCardsInHand(command);
            case "getFrozenCardsOnTable" -> new GetFrozenCardsOnTable();
            case "getTotalGamesPlayed" -> new GetTotalGamesPlayed();
            case "getPlayerOneWins" -> new GetPlayerOneWins();
            case "getPlayerTwoWins" -> new GetPlayerTwoWins();
            case "placeCard" -> new PlaceCard(command);
            case "cardUsesAttack" -> new CardUsesAttack(command);
            case "useEnvironmentCard" -> new UseEnvironmentCard(command);
            case "cardUsesAbility" -> new CardUsesAbility(command);
            case "useAttackHero" -> new UseAttackHero(command);
            case "useHeroAbility" -> new UseHeroAbility(command);
            default -> new EndPlayerTurn();
        };
    }
    protected void newRound(final ArrayList<Player> player, final int mana) {
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
    protected void shuffleDecks(final ArrayList<Player> player, final int seed) {
        Collections.shuffle(player.get(0).getDeck().getCards(), new Random(seed));
        Collections.shuffle(player.get(1).getDeck().getCards(), new Random(seed));
    }
}
