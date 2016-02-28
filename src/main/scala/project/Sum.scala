package project

/**
  * Created by Shock on 2/28/2016.
  */
class Sum(val first: Money, val second: Money) extends Expression {
  override def reduce(bank:Bank, currency: String): Money = {
    val sum = first.reduce(bank, currency).amount + second.reduce(bank, currency).amount
    new Money(sum, currency)
  }
}