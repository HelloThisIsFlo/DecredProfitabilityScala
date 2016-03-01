package project

import org.json4s.DefaultFormats
import org.json4s.native.JsonMethods
import org.scalatest.{BeforeAndAfter, FunSuite}

/**
  * Created by Shock on 3/1/2016.
  */
class LearningTestJson4s extends FunSuite with BeforeAndAfter{

  val jsonString = """
    { "name" : "Toddler", "age" : 2, "greeting": "gurgle!" }
             """
  val jsonRatesString = """{
                            "usd": "1.58006",
                            "eur": "1.45522261952",
                            "cny": "10.3560687535",
                            "cad": "2.12919089228",
                            "rub": "117.34367573974",
                            "btc": "0.00365025"
                          }"""


  before {

  }

  test("Parse a simple json string") {
    val json = JsonMethods.parse(jsonString)

    implicit val formats = DefaultFormats // I have no idea what this actually does!
    val toddler = json.extract[Toddler]

    assert(toddler == Toddler("Toddler", 2, "gurgle!"))
  }

  test("Parse rates") {
    val json = JsonMethods.parse(jsonRatesString)

    implicit val formats = DefaultFormats
    val rates = json.extract[Rates]

    assert(rates.usd.nonEmpty && rates.eur.nonEmpty)
  }
}


case class Rates(usd: String, eur: String, cny: String, cad: String, rub: String, btc: String)
case class Toddler(name: String, age: Int, greeting: String)
