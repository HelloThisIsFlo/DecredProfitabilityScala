package project


import org.scalatest.FunSuite
import com.stackmob.newman._
import com.stackmob.newman.dsl._
import scala.concurrent._
import scala.concurrent.duration._
import java.net.URL

/**
  * Created by Shock on 3/1/2016.
  */
class LearningTestNewton extends FunSuite{


  test("Get the rate from the Api") {
    pending
    implicit val httpClient = new ApacheHttpClient
    val url = new URL("http://coinmarketcap-nexuist.rhcloud.com/api/dcr/price")
    val response = Await.result(GET(url).apply, 2.second)
    assert(response.bodyString.startsWith("{\n  \"usd\":"))
  }
}
