package project

import org.scalatest.{BeforeAndAfter, FunSuite}
import project.currency.{Bank, Money}

/**
  * Created by Shock on 2/28/2016.
  */
class TestConsumption extends FunSuite with BeforeAndAfter {

  val CONSUMPTION_IDLE = 65.0
  val CONSUMPTION_MINING = 320.0
  val PRICE_PER_KWH = Money.dollar(0.11)

  var consumption:Consumption = _

  before{
    val bank = new Bank()
    consumption = new Consumption(bank, CONSUMPTION_IDLE, CONSUMPTION_MINING, PRICE_PER_KWH)
  }

  test("Calculate extra consumption while mining") {
    val expected = CONSUMPTION_MINING - CONSUMPTION_IDLE
    assert(expected == consumption.extraPowerWhileMining)
  }

  test("Calculate mining cost given a number of hours") {
    val hours = 4
    val expected = PRICE_PER_KWH.amount * (CONSUMPTION_MINING - CONSUMPTION_IDLE) * hours / 1000
    assert(expected == consumption.getMiningCost(hours, Money.DOLLAR_CURRENCY).amount)
  }



}
