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

}