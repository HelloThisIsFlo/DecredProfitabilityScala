package project.currency

object Money {

  val DECRED_CURRENCY: String = "DEC"
  val DOLLAR_CURRENCY: String = "USD"
  val EURO_CURRENCY: String = "EUR"

  def dollar(amount: Double): Money = {
    new Money(amount, DOLLAR_CURRENCY)
  }

  def decred(amount: Double): Money = {
    new Money(amount, DECRED_CURRENCY)
  }

  def euro(amount: Double): Money = {
    new Money(amount, EURO_CURRENCY)
  }
}

class Money(val amount: Double, val currency:String)  extends Expression {

  def times(factor: Double): Money = {
    new Money(this.amount * factor, this.currency)
  }

  def dividedBy(factor: Double): Money = {
    new Money(this.amount / factor, this.currency)
  }

  override def reduce(bank: Bank, currency: String): Money =  {
    val rate = bank.getRate(this.currency, currency)
    new Money(rate * this.amount, currency)
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[Money]

  override def equals(other: Any): Boolean = other match {
    case that: Money =>
      (that canEqual this) &&
        areAmountsEqual(other.asInstanceOf[Money]) &&
        currency == that.currency
    case _ => false
  }

  def areAmountsEqual(other: Money): Boolean = {
    val diff = Math.abs(this.amount - other.amount)
    if (diff < 0.0000000001) {
      true
    } else {
      false
    }
  }

  override def hashCode(): Int = {
    val state = Seq(amount, currency)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  override def toString: String = {
    amount + " " + currency
  }
}