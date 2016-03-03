package project

import java.net.URL

import com.stackmob.newman.ApacheHttpClient
import com.stackmob.newman.dsl._
import org.json4s.DefaultFormats
import org.json4s.native.JsonMethods
import org.scalatest.{BeforeAndAfter, FunSuite}
import scala.concurrent.duration._


import scala.concurrent.Await

/**
  * Created by Shock on 3/3/2016.
  */
class TestGetRatesFromInternet extends FunSuite with BeforeAndAfter{

  test("Get the rates from the internet") {
    implicit val httpClient = new ApacheHttpClient
    val url = new URL("http://coinmarketcap-nexuist.rhcloud.com/api/dcr/price")

    val request = GET(url)
    val response = Await.result(request.apply, 2.second)
    assert(response.bodyString.startsWith("{\n  \"usd\":"))
  }

  test("Parse rates from response") {
    val jsonRatesString = """{
                            "usd": "1.58006",
                            "eur": "1.45522261952",
                            "cny": "10.3560687535",
                            "cad": "2.12919089228",
                            "rub": "117.34367573974",
                            "btc": "0.00365025"
                          }"""

    val json = JsonMethods.parse(jsonRatesString)

    implicit val formats = DefaultFormats
    val rates = json.extract[Rates]

    assert(rates.usd.nonEmpty && rates.eur.nonEmpty)
    assert(rates.usd.toDouble == 1.58006)
  }

  case class Rates(usd: String, eur: String, cny: String, cad: String, rub: String, btc: String)

}
