package project

import org.scalatest.{BeforeAndAfter, FunSuite}
import project.currency.{Money, Bank}

/**
  * Created by Shock on 2/28/2016.
  */
class TestProfitability extends FunSuite with BeforeAndAfter {

  val PRODUCTION_PER_HOUR = Money.decred(0.1388888888888889)

  val CONSUMPTION_IDLE: Double = 63
  val CONSUMPTION_MINING: Double = 320
  val PRICE_PER_KWH = Money.dollar(0.38)

  val DECRED_TO_DOLLAR = 2.23
  val DOLLAR_TO_EURO = 0.91

  var bank:Bank = _
  var profitability:Profitability = _

  before {
    bank = new Bank
    bank.addRate(Money.DECRED_CURRENCY, Money.DOLLAR_CURRENCY, DECRED_TO_DOLLAR)
    bank.addRate(Money.DECRED_CURRENCY, Money.EURO_CURRENCY, DECRED_TO_DOLLAR * DOLLAR_TO_EURO)
    bank.addRate(Money.DOLLAR_CURRENCY, Money.EURO_CURRENCY, DOLLAR_TO_EURO)
    val production = new Production(bank, PRODUCTION_PER_HOUR)
    val consumption = new Consumption(bank, CONSUMPTION_IDLE, CONSUMPTION_MINING, PRICE_PER_KWH)

    profitability = new Profitability(bank, production, consumption)
  }

  test("Get earning in Dollar given the production, the consumption, and the number of hours") {
    val expectedPerHour = makeExpectedEarningPerHourInDollar(bank)
    val hours = 5
    assert(expectedPerHour.times(hours) == profitability.getEarnings(hours, Money.DOLLAR_CURRENCY))
  }

  test("Get earning in Euro given the production, the consumption, and the number of hours") {
    val expectedPerHour = Money.euro(makeExpectedEarningPerHourInDollar(bank).amount * DOLLAR_TO_EURO)
    val hours = 5235
    assert(expectedPerHour.times(hours) == profitability.getEarnings(hours, Money.EURO_CURRENCY))
  }

  def makeExpectedEarningPerHourInDollar(bank: Bank): Money = {
    val extraConsumption = CONSUMPTION_MINING - CONSUMPTION_IDLE
    val extraConsumptionKwh = extraConsumption / 1000
    val spentInOneHour = PRICE_PER_KWH.times(extraConsumptionKwh)

    val earnedInONeHour = PRODUCTION_PER_HOUR.minus(spentInOneHour)

    bank.reduce(earnedInONeHour, Money.DOLLAR_CURRENCY)
  }

}
