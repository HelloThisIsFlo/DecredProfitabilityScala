package project

/**
  * Represents an expression.
  *
  * Could be a sum, a Money, or else . . . .
  */
trait Expression {
  def reduce(bank: Bank, currency: String): Money

  def plus(other: Expression): Expression = {
    new Sum(this, other)
  }
}


