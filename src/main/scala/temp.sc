
import scala.util.Try

// Somes types just to get meaningful method signatures
type CoffeeBeans = String
type GroundCofee = String
case class Water(temperature: Int)
type Milk = String
type FrothedMilk = String
type Espresso = String
type Cappuccino = String

// Dummy implementations of the individual steps :
def grind(beans: CoffeeBeans): GroundCofee = s"Ground coffee of $beans"
def heatWater(water: Water): Water = water.copy(temperature = 85)
def frothMilk(milk: Milk): FrothedMilk = s"frothed $milk"
def brew(coffee: GroundCofee, heatedwater: Water): Espresso = "Espresso"
def combine(espresso: Espresso, frothedMilk: FrothedMilk): Cappuccino = "Cappucino"

// Some exceptions for things that might go wrong in the individual steps
case class GrindingException(msg: String) extends Exception(msg)
case class FrothingException(msg: String) extends Exception(msg)
case class WaterBoilingException(msg: String) extends Exception(msg)
case class BrewingException(msg: String) extends Exception(msg)

// Going through these steps sequentially
def prepareCappuccino(): Try[Cappuccino] = for {
  ground <- Try(grind("arabica beans"))
  water <- Try(heatWater(Water(23)))
  espresso <- Try(brew(ground, water))
  foam <- Try(frothMilk("milk"))
} yield combine(espresso, foam)

prepareCappuccino()