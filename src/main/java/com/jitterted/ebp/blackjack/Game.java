package com.jitterted.ebp.blackjack;

import org.fusesource.jansi.Ansi;

import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class Game {

  private final Deck deck;

  private final Hand dealerHand = new Hand();
  private final Hand playerHand = new Hand();

  public static void main(String[] args) {
    displayWelcome();
    playGame();
    resetScreen();
  }

  private static void resetScreen() {
    System.out.println(ansi().reset());
  }

  private static void playGame() {
    Game game = new Game();
    game.initialDeal();
    game.play();
  }

  private static void displayWelcome() {
    System.out.println(ansi()
                           .bgBright(Ansi.Color.WHITE)
                           .eraseScreen()
                           .cursor(1, 1)
                           .fgGreen().a("Welcome to")
                           .fgRed().a(" Jitterted's")
                           .fgBlack().a(" BlackJack"));
  }

  public Game() {
    deck = new Deck();
  }

  public void initialDeal() {
    dealRoundOfCards();
    dealRoundOfCards();
  }

  private void dealRoundOfCards() {
    // deal players first, because that's the rules of the game
    playerHand.drawCardFrom(deck);
    dealerHand.drawCardFrom(deck);
  }

  public void play() {
    boolean playerBusted = false;
    playerBusted = playerPlays(playerBusted);

    dealerPlays(playerBusted);
    displayFinalGameState();
    displayGameOutcome(playerBusted);
  }

  private void displayGameOutcome(boolean playerBusted) {
    if (playerBusted) {
      System.out.println("You Busted, so you lose.  💸");
    } else if (dealerHand.isBusted()) {
      System.out.println("Dealer went BUST, Player wins! Yay for you!! 💵");
    } else if (playerHand.beats(dealerHand)) {
      System.out.println("You beat the Dealer! 💵");
    } else if (playerHand.pushesWith(dealerHand)) {
      System.out.println("Push: The house wins, you Lose. 💸");
    } else {
      System.out.println("You lost to the Dealer. 💸");
    }
  }

  private void dealerPlays(boolean playerBusted) {
    // Dealer makes its choice automatically based on a simple heuristic (<=16, hit, 17>stand)
    if (!playerBusted) {
      while (dealerHand.hasValueLessThanOrEqualTo(16)) {
        dealerHand.drawCardFrom(deck);
      }
    }
  }

  private boolean playerPlays(boolean playerBusted) {
    // get Player's decision: hit until they stand, then they're done (or they go bust)
    while (!playerBusted) {
      displayGameState();
      String playerChoice = inputFromPlayer().toLowerCase();
      if (playerStands(playerChoice)) {
        break;
      }
      if (playerHits(playerChoice)) {
        playerHand.drawCardFrom(deck);
        if (playerHand.isBusted()) {
          playerBusted = true;
        }
      } else {
        System.out.println("You need to [H]it or [S]tand");
      }
    }
    return playerBusted;
  }

  private boolean playerHits(String playerChoice) {
    return playerChoice.startsWith("h");
  }

  private boolean playerStands(String playerChoice) {
    return playerChoice.startsWith("s");
  }

  private String inputFromPlayer() {
    System.out.println("[H]it or [S]tand?");
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }

  private void displayGameState() {
    displayDealerHand();
    System.out.println();
    displayPlayerHand();
  }

  private void displayDealerHand() {
    displayDealerUpCard();

    // second card is the hole card, which is hidden, because that's the rules
    displayBackOfCard();
  }

  private void displayDealerUpCard() {
    System.out.print(ansi().eraseScreen().cursor(1, 1));
    System.out.println("Dealer has: ");
    System.out.println(dealerHand.displayOfFirstCard()); // first card is Face Up
  }

  private void displayBackOfCard() {
    System.out.print(
        ansi()
            .cursorUp(7)
            .cursorRight(12)
            .a("┌─────────┐").cursorDown(1).cursorLeft(11)
            .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
            .a("│░ J I T ░│").cursorDown(1).cursorLeft(11)
            .a("│░ T E R ░│").cursorDown(1).cursorLeft(11)
            .a("│░ T E D ░│").cursorDown(1).cursorLeft(11)
            .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
            .a("└─────────┘"));
  }

  private void displayFinalGameState() {
    System.out.print(ansi().eraseScreen().cursor(1, 1));

    displayFinalDealerHand();

    System.out.println();

    displayPlayerHand();
  }

  private void displayPlayerHand() {
    System.out.println("Player has: ");
    playerHand.display();
    System.out.println(" (" + playerHand.displayValue() + ")");
  }

  private void displayFinalDealerHand() {
    System.out.println("Dealer has: ");
    dealerHand.display();
    System.out.println(" (" + dealerHand.displayValue() + ")");
  }

}
