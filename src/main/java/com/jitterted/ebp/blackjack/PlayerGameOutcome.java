package com.jitterted.ebp.blackjack;

public enum PlayerGameOutcome {
  WINS(2.0),
  LOSES(0.0),
  PUSHES(1.0),
  BLACKJACK(2.5);

  private final double payoffMultiplier;

  PlayerGameOutcome(double payoffMultiplier) {
    this.payoffMultiplier = payoffMultiplier;
  }


  public int payoffFor(int playerBet) {
    return (int) (playerBet * payoffMultiplier);
  }
}
