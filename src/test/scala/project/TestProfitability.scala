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

  test("Get earning in Dollar given the production, the consumption, and the number of hours") {
    val bank = new Bank
    bank.addRate(Money.DECRED_CURRENCY, Money.DOLLAR_CURRENCY, DECRED_TO_DOLLAR)
    val production = new Production(bank, PRODUCTION_PER_HOUR)
    val consumption = new Consumption(bank, CONSUMPTION_IDLE, CONSUMPTION_MINING, PRICE_PER_KWH)

    val profitability = new Profitability(bank, production, consumption)

    val expectedPerHour = makeExpectedEarningPerHourInDollar(bank)
    val hours = 51
    assert(expectedPerHour.times(hours) == profitability.getEarnings(hours, Money.DOLLAR_CURRENCY))

    println(profitability.getEarnings(700, Money.DOLLAR_CURRENCY).amount * 0.91)
  }

  def makeExpectedEarningPerHourInDollar(bank: Bank): Money = {
    val extraConsumption = CONSUMPTION_MINING - CONSUMPTION_IDLE
    val extraConsumptionKwh = extraConsumption / 1000
    val spentInOneHour = PRICE_PER_KWH.times(extraConsumptionKwh)

    val earnedInONeHour = PRODUCTION_PER_HOUR.minus(spentInOneHour)

    bank.reduce(earnedInONeHour, Money.DOLLAR_CURRENCY)
  }

}
