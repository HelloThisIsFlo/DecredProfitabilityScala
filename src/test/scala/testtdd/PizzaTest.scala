package testtdd

import org.scalatest.{BeforeAndAfter, FunSuite}

/**
  * Created by Shock on 2/28/2016.
  */
class PizzaTest extends FunSuite with BeforeAndAfter{

  var pizza: Pizza = _

  val TOPPING_NAME = "peperroni"

  before {
    pizza = new Pizza
  }

  test("New pizza has zero toppings") {
    assert(pizza.getToppings().isEmpty)
  }

  test("Add one topping -> 1 topping on the pizza") {
    pizza.addTopping(Topping(TOPPING_NAME))
    assert(pizza.getToppings().contains(Topping(TOPPING_NAME)))
  }

  test("Remove topping") {
    pizza.addTopping(Topping(TOPPING_NAME))
    assert(pizza.getToppings().contains(Topping(TOPPING_NAME)))
    pizza.removeTopping(Topping(TOPPING_NAME))
    assert(!pizza.getToppings().contains(Topping(TOPPING_NAME)))
  }

  test("test pizza pricing") (pending)
}
