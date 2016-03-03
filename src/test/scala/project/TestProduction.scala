package project

import org.scalatest.{BeforeAndAfter, FunSuite}
import project.currency.{Bank, Money}

/**
  * Created by Shock on 2/28/2016.
  */
class TestProduction extends FunSuite with BeforeAndAfter{

  val PRODUCTION_PER_HOUR = Money.decred(0.1)
  val bank = {
    new Bank
  }

  test("Get the production in Decred, given a number of hours") {
    val production = new Production(bank, PRODUCTION_PER_HOUR)
    val hours = 12
    val expected = PRODUCTION_PER_HOUR.amount * hours

    assert(Money.decred(expected) == production.getProduction(hours, Money.DECRED_CURRENCY))
  }

  test("Get the number of hours spent to produce a certain ammount of Decred") {
    val production = new Production(bank, PRODUCTION_PER_HOUR)
    val produced = Money.decred(1)

    val expectedHours = 10
    assert(expectedHours == production.getHoursSpent(produced))
  }


}
