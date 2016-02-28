package project

import project.currency.{Money, Bank}

/**
  * Created by Shock on 2/28/2016.
  */
class Production(bank: Bank, productionPerHour: Money) {

  def getProduction(hours: Double, currency: String): Money = {
    val production = productionPerHour.times(hours)
    bank.reduce(production, currency)
  }
}
