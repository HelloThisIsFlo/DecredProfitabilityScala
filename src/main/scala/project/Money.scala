package project

object Money {

  val DECRED_CURRENCY: String = "DEC"
  val DOLLAR_CURRENCY: String = "USD"

  def dollar(amount: Double): Money = {
    new Money(amount, DOLLAR_CURRENCY)
  }

  def decred(amount: Double): Money = {
    new Money(amount, DECRED_CURRENCY)
  }
}

class Money(val amount: Double, val currency:String)  extends Expression {

  def times(factor: Double): Money = {
    new Money(this.amount * factor, this.currency)
  }

  def plus(other: Money): Money = {
    new Money(this.amount + other.amount, this.currency)
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[Money]

  override def equals(other: Any): Boolean = other match {
    case that: Money =>
      (that canEqual this) &&
        amount == that.amount &&
        currency == that.currency
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(amount, currency)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}