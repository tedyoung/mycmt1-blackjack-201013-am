package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameBettingTest {

  @Test
  public void newGamePlayerBalanceIsZero() throws Exception {
    Game game = new Game();

    assertThat(game.playerBalance())
        .isZero();
  }

  @Test
  public void playerDeposits100BalanceIs100() throws Exception {
    Game game = new Game();

    game.playerDeposits(100);

    assertThat(game.playerBalance())
        .isEqualTo(100);
  }

  @Test
  public void playerWith100Best25BalanceIs75() throws Exception {
    Game game = new Game();
    game.playerDeposits(100);

    game.playerBets(25);

    assertThat(game.playerBalance())
        .isEqualTo(75);
  }

  @Test
  public void playerWith100Bets50AndWinsBalanceIs150() throws Exception {
    Game game = new Game();
    game.playerDeposits(100);
    game.playerBets(50);

    game.playerWins();

    assertThat(game.playerBalance())
        .isEqualTo(100 - 50 + (2 * 50));
  }

  @Test
  public void playerWith80Bets60AndLosesBalanceIs20() throws Exception {
    Game game = new Game();
    game.playerDeposits(80);
    game.playerBets(60);

    game.playerLoses();

    assertThat(game.playerBalance())
        .isEqualTo(80 - 60);
  }

  @Test
  public void playerWith30Bets20AndPushesBalanceIs30() throws Exception {
    Game game = new Game();
    game.playerDeposits(30);
    game.playerBets(20);

    game.playerPushes();

    assertThat(game.playerBalance())
        .isEqualTo(30);
  }

  @Test
  public void playerWith200Bets200AndWinsBlackjack500() throws Exception {
    Game game = new Game();
    game.playerDeposits(200);
    game.playerBets(200);

    game.playerWinsBlackjack();

    assertThat(game.playerBalance())
        .isEqualTo((int) (200 * 2.5));
  }

}


