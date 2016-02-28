package project

import org.scalatest.{BeforeAndAfter, FunSuite}

/**
  * Created by Shock on 2/28/2016.
  */
class TestCurrency extends FunSuite with BeforeAndAfter {

  val DECRED_CURRENCY = Money.DECRED_CURRENCY
  val DOLLAR_CURRENCY = Money.DOLLAR_CURRENCY

  var fiveDecred: Money = _
  var eightDecred: Money = _
  var fiveDollar: Money = _
  var eightDollar: Money = _

  before {
    fiveDecred = Money.decred(5)
    eightDecred = Money.decred(8)
    fiveDollar = Money.dollar(5)
    eightDollar = Money.dollar(8)
  }

  test("Test multiplication : 5$ * 8 = 40") {
    val result = fiveDollar.times(8)
    assert(Money.dollar(5 * 8) == result)
  }

  test("Test equality with Money") {
    val otherFive: Money = Money.decred(5)
    assert(otherFive == fiveDecred)
    val otherEight: Money = Money.decred(8)
    assert(otherEight != fiveDecred)
    val otherFiveDollar: Money = Money.dollar(5)
    assert(otherFiveDollar != fiveDecred)
  }

  test("Check if the right currency is used") {
    assert(fiveDecred.currency == DECRED_CURRENCY)
    assert(eightDollar.currency == DOLLAR_CURRENCY)
  }

  test("Test simple addition using the Bank to resolve currencies : 5$ + 8$ = 13$") {
    val sum:Expression = fiveDollar.plus(eightDollar)
    val bank = new Bank
    val result = bank.reduce(sum, Money.DOLLAR_CURRENCY)
    assert(result == Money.dollar(5 + 8))
  }

  test("Test reduce sum") {
    val sum = new Sum(Money.dollar(1), Money.dollar(2))
    val bank = new Bank
    val result = bank.reduce(sum, Money.DOLLAR_CURRENCY)
    assert(result == Money.dollar(3))
  }

  test("Test reduce money : 1DEC == 1DEC") {
    val bank = new Bank
    val result = bank.reduce(Money.dollar(1), Money.DOLLAR_CURRENCY)
    assert(Money.dollar(1) == result)
  }

  test("Test reduce money with different currencies") {
    val bank = new Bank
    bank.addRate(Money.DECRED_CURRENCY, Money.DOLLAR_CURRENCY, 2)
    val result = bank.reduce(Money.decred(1), Money.DOLLAR_CURRENCY)
    assert(Money.dollar(2) == result)
  }

  test("Rate not available => Throw exception") {
    val bank = new Bank
    intercept[RateNotDefinedException] {
      bank.reduce(Money.decred(1), Money.DOLLAR_CURRENCY)
    }
  }

  test("1 Decred + 1 Dollar == 3 Dollar if conversion rate is : 1DEC -> 2USD") {
    val oneDollar:Expression = Money.dollar(1)
    val oneDecred:Expression = Money.decred(1)

    val sum:Expression = oneDecred.plus(oneDollar)
    val bank = new Bank()
    bank.addRate(Money.DECRED_CURRENCY, Money.DOLLAR_CURRENCY, 2)
    val result = bank.reduce(sum, Money.DOLLAR_CURRENCY)
    assert(result == Money.dollar(3))
  }

  test("Test the sum of a sum") {
    val sumEquals3Dollars = Money.dollar(1).plus(Money.decred(1))
    val twoDollar = Money.dollar(2)

    val totalsum = sumEquals3Dollars.plus(twoDollar)
    val bank = new Bank
    bank.addRate(Money.DECRED_CURRENCY, Money.DOLLAR_CURRENCY, 2)
    val result = bank.reduce(totalsum, Money.DOLLAR_CURRENCY)

    assert(result == Money.dollar(5))
  }
}
