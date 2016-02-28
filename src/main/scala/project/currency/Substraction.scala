package project.currency

/**
  * Created by Shock on 2/28/2016.
  */
class Substraction(first: Expression, second: Expression) extends Expression{
  override def reduce(bank: Bank, currency: String): Money = {
    val subResult = bank.reduce(first, currency).amount - bank.reduce(second, currency).amount
    new Money(subResult, currency)
  }
}
