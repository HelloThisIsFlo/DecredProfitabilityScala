package project

import scala.collection.mutable


/**
  * Created by Shock on 2/28/2016.
  */
class Bank {

  private val rates: mutable.Map[CurrencyPair, Double] = mutable.Map[CurrencyPair, Double]() // todo find a way with a immutable object

  def addRate(fromCurrency: String, toCurrency: String, rate: Double): Unit = {
    rates += (CurrencyPair(fromCurrency, toCurrency) -> rate)
  }

  def getRate(fromCurrency: String, toCurrency: String): Double = {
    if (fromCurrency == toCurrency) {
      return 1
    }
    val rate = rates.get(CurrencyPair(fromCurrency, toCurrency))
    rate match {
      case None => throw new RateNotDefinedException
      case isDefined => rate.get
    }
  }

  def reduce(expression: Expression, currency: String): Money = {
    expression.reduce(this, currency)
  }

  private case class CurrencyPair(fromCurrency: String, toCurrency: String)
}


class RateNotDefinedException extends Exception