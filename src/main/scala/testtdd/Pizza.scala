package testtdd
import scala.collection.mutable.ArrayBuffer

/**
  * Created by Shock on 2/28/2016.
  */
class Pizza {

  val toppings: ArrayBuffer[Topping] = ArrayBuffer()

  def getToppings(): ArrayBuffer[Topping] = {
    toppings
  }

  def addTopping(topping: Topping): Unit = {
    toppings += topping
  }

  def removeTopping(topping: Topping): Unit = {
    toppings -= topping
  }

}

case class Topping(name: String)