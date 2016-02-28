package project

import project.currency.{Money, Bank}

/**
  * Created by Shock on 2/28/2016.
  */
class Profitability(bank: Bank, production: Production, consumption: Consumption) {

  def getEarnings(hours: Double, currency: String): Money = {

    val produced = production.getProduction(hours, currency)
    val spent = consumption.getMiningCost(hours, currency)
    val result = produced.minus(spent)

    bank.reduce(result, currency)
  }
}
