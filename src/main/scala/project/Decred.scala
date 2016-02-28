package project

/**
  * Value object representing a Decred
  */
case class Decred(amount: Double) {

  def plus(other: Decred): Decred = {
    Decred(this.amount + other.amount)
  }
}
