package project

import org.scalatest.{BeforeAndAfter, FunSuite}
import project.currency._

/**
  * Created by Shock on 2/28/2016.
  */
class TestCurrency extends FunSuite with BeforeAndAfter {

  var bank: Bank = _

  before {
    bank = new Bank()
    bank.addRate(Money.DECRED_CURRENCY, Money.DOLLAR_CURRENCY, 2)
  }

  test("Test multiplication : 5$ * 8 = 40") {
    val fiveDollar = Money.dollar(5)
    val result = fiveDollar.times(8)
    assert(Money.dollar(5 * 8) == result)
  }

  test("Test equality with Money") {
    assert(Money.decred(5) == Money.decred(5))
    assert(Money.decred(5) != Money.decred(8))
    assert(Money.dollar(5) != Money.decred(5))
  }

  test("Check if the right currency is used") {
    assert(Money.decred(5).currency == Money.DECRED_CURRENCY)
    assert(Money.dollar(8).currency == Money.DOLLAR_CURRENCY)
  }

  test("Test reduce money with different currencies") {
    val result = bank.reduce(Money.decred(1), Money.DOLLAR_CURRENCY)
    assert(Money.dollar(2) == result)
  }

  test("Rate not available => Throw exception") {
    val bank = new Bank
    intercept[RateNotDefinedException] {
      bank.reduce(Money.decred(1), Money.DOLLAR_CURRENCY)
    }
  }

  test("Test reduce Sum : 1 Decred + 1 Dollar == 3 Dollar if conversion rate is : 1DEC -> 2USD") {
    val oneDollar:Expression = Money.dollar(1)
    val oneDecred:Expression = Money.decred(1)

    val sum:Expression = oneDecred.plus(oneDollar)
    val result = bank.reduce(sum, Money.DOLLAR_CURRENCY)
    assert(result == Money.dollar(3))
  }

  test("Test the sum of a sum") {
    val sumEquals3Dollars = Money.dollar(1).plus(Money.decred(1))
    val twoDollar = Money.dollar(2)

    val totalsum = sumEquals3Dollars.plus(twoDollar)
    val result = bank.reduce(totalsum, Money.DOLLAR_CURRENCY)

    assert(result == Money.dollar(5))
  }

  test("Test substraction") {
    val equalsFiveDollars = Money.dollar(7).minus(Money.decred(1)) // =5 with current conversion rate
    val oneDollar = Money.dollar(1)

    val totalSubstraction = equalsFiveDollars.minus(oneDollar)
    val result = bank.reduce(totalSubstraction, Money.DOLLAR_CURRENCY)

    assert(result == Money.dollar(4))
  }

  test("Division of a Money => Return a Money same currency") {
    val tenDollar = Money.dollar(10)
    val division = tenDollar.dividedBy(3)

    assert(division == Money.dollar(10.0 / 3))
  }
}
