package project

/**
  * Represents an expression.
  *
  * Could be a sum, a Money, or else . . . .
  */
trait Expression {

}

class Sum(first: Expression, second: Expression) extends Expression
