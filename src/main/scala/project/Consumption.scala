package project

import project.currency.{Bank, Money}

/**
  * Created by Shock on 2/28/2016.
  */
class Consumption(bank: Bank, idle: Double, mining: Double, priceKwh: Money) {

  def extraPowerWhileMining: Double = {
    mining - idle
  }

  def getMiningCost(hours: Double, currency: String): Money = {
    val priceInOriginalCurrency = priceKwh.times(extraPowerWhileMining/1000 * hours)
    bank.reduce(priceInOriginalCurrency, currency)
  }
}
