package project

import org.scalatest.{BeforeAndAfter, FunSuite}

/**
  * Created by Shock on 2/28/2016.
  */
class TestCurrency extends FunSuite with BeforeAndAfter {

  val DECRED_TO_DOLLAR = 2

  val DECRED_CURRENCY = Money.DECRED_CURRENCY
  val DOLLAR_CURRENCY = Money.DOLLAR_CURRENCY

  var fiveDecred: Decred = _
  var eightDecred: Decred = _
  var fiveDollar: Dollar = _
  var eightDollar: Dollar = _

  before {
    fiveDecred = Money.decred(5)
    eightDecred = Money.decred(8)
    fiveDollar = Money.dollar(5)
    eightDollar = Money.dollar(8)
  }

  test("Test addition on Decred") {
    val result = fiveDecred.plus(eightDecred)
    assert(Money.decred(5 + 8) == result)
  }

  test("Test multiplication on Decred") {
    val result = fiveDecred.times(8)
    assert(Money.decred(5 * 8) == result)
  }

  test("Test multiplication on Dollar") {
    val result = fiveDollar.plus(eightDollar)
    assert(Money.dollar(5 + 8) == result)
  }

  test("Test addition on Dollar") {
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

  test("1 Decred + 1 Dollar == 3 Dollar if conversion rate is : 1DEC == 2USD") {
    val sum:Expression = new Sum(Money.decred(1), Money.decred(1))
    val bank = new Bank()
    bank.addRateConversionToDollar(Money.DECRED_CURRENCY, DECRED_TO_DOLLAR)
    val result = bank.reduce(sum, Money.DOLLAR_CURRENCY)
    assert(result == Money.dollar(3))
  }
}
