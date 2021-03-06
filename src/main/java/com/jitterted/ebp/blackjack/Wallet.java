package com.jitterted.ebp.blackjack;

public class Wallet {
  private int balance = 0;

  public boolean isEmpty() {
    return balance == 0;
  }

  public void addMoney(int amount) {
    requireAmountGreaterThanZero(amount);
    balance += amount;
  }

  public int balance() {
    return balance;
  }

  public void bet(int amount) {
    requireSufficientBalanceFor(amount);
    balance -= amount;
  }

  private void requireSufficientBalanceFor(int amount) {
    if (balance < amount) {
      throw new IllegalArgumentException();
    }
  }

  private void requireAmountGreaterThanZero(int amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException();
    }
  }
}
