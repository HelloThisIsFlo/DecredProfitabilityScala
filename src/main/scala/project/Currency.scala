package project

object Money {

  val DECRED_CURRENCY: String = "DEC"
  val DOLLAR_CURRENCY: String = "USD"

  def dollar(amount: Double): Dollar = {
    Dollar(amount, DOLLAR_CURRENCY)
  }

  def decred(amount: Double): Decred = {
    Decred(amount, DECRED_CURRENCY)
  }
}

trait Money {
  def amount: Double
  def currency: String
}

case class Decred(amount: Double, currency: String) extends Money {
  def plus(other: Decred): Money = {
    Decred(this.amount + other.amount, this.currency)
  }

  def times(factor: Double): Money = {
    Money.decred(this.amount * factor)
  }
}

case class Dollar(amount: Double, currency: String) extends Money {
  def plus(other: Dollar): Money = {
    Dollar(this.amount + other.amount, this.currency)
  }

  def times(factor: Double): Money = {
    Money.dollar(this.amount * factor)
  }
}
