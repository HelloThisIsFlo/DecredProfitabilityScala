package project

import project.currency.{Bank, Money}

/**
  * Created by Shock on 2/28/2016.
  */
class Production(bank: Bank, productionPerHour: Money) {

  def getProduction(hours: Double, currency: String): Money = {
    val production = productionPerHour.times(hours)
    bank.reduce(production, currency)
  }

  def getHoursSpent(produced: Money): Double = {
    val producedInDecred = bank.reduce(produced, Money.DECRED_CURRENCY)
    val productionPerHourInDecred = bank.reduce(productionPerHour, Money.DECRED_CURRENCY)
    producedInDecred.amount / productionPerHour.amount
  }
}
