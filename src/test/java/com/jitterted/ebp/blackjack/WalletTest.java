package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class WalletTest {

  @Test
  public void newWalletIsEmpty() throws Exception {
    Wallet wallet = new Wallet();

    assertThat(wallet.isEmpty())
        .isTrue();
  }

  @Test
  public void addMoneyToNewWalletIsNotEmpty() throws Exception {
    Wallet wallet = new Wallet();

    wallet.addMoney(10);

    assertThat(wallet.isEmpty())
        .isFalse();
  }

  @Test
  public void newWalletHasZeroBalance() throws Exception {
    Wallet wallet = new Wallet();

    assertThat(wallet.balance())
        .isZero();
  }

  @Test
  public void newWalletAdd1BalanceIs1() throws Exception {
    Wallet wallet = new Wallet();

    wallet.addMoney(1);

    assertThat(wallet.balance())
        .isEqualTo(1);
  }

  @Test
  public void newWalletAdd13AndAdd16BalanceIs29() throws Exception {
    Wallet wallet = new Wallet();

    wallet.addMoney(13);
    wallet.addMoney(16);

    assertThat(wallet.balance())
        .isEqualTo(13 + 16);
  }

  @Test
  public void addMoneyWithZeroThrowsException() throws Exception {
    Wallet wallet = new Wallet();

    assertThatThrownBy(() -> {
      wallet.addMoney(0);
    }).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void addNegativeMoneyThrowsException() throws Exception {
    Wallet wallet = new Wallet();

    assertThatThrownBy(() -> {
      wallet.addMoney(-1);
    }).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void walletWith18Bet14HasBalanceOf4() throws Exception {
    Wallet wallet = new Wallet();
    wallet.addMoney(18);

    wallet.bet(14);

    assertThat(wallet.balance())
        .isEqualTo(18 - 14);
  }

  @Test
  public void wallet35Bet16AndBet17HasBalanceOf2() throws Exception {
    Wallet wallet = new Wallet();
    wallet.addMoney(35);

    wallet.bet(16);
    wallet.bet(17);

    assertThat(wallet.balance())
        .isEqualTo(35 - 16 - 17);
  }

  @Test
  public void betFullAmountInWalletIsEmptyIsTrue() throws Exception {
    Wallet wallet = new Wallet();
    wallet.addMoney(20);

    wallet.bet(20);

    assertThat(wallet.isEmpty())
        .isTrue();
  }

  @Test
  public void betMoreThanBalanceThrowsException() throws Exception {
    Wallet wallet = new Wallet();
    wallet.addMoney(12);

    assertThatThrownBy(() -> {
      wallet.bet(13);
    }).isInstanceOf(IllegalArgumentException.class);
  }

}

