package project

import org.scalatest.{BeforeAndAfter, FunSuite}

/**
  * Created by Shock on 2/28/2016.
  */
class TestDecred extends FunSuite with BeforeAndAfter {

  test("Test addition") {
    val five = Decred(5)
    val eight = Decred(8)
    val result = five.plus(eight)
    assert(Decred(5 + 8) == result)
  }

}
