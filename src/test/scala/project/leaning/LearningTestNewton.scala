package project.leaning

import java.net.URL

import com.stackmob.newman._
import com.stackmob.newman.dsl._
import org.scalatest.FunSuite

import scala.concurrent._
import scala.concurrent.duration._

/**
  * Created by Shock on 3/1/2016.
  */
class LearningTestNewton extends FunSuite{


  test("Get the rate from the Api") {
    pending
    implicit val httpClient = new ApacheHttpClient
    val url = new URL("http://coinmarketcap-nexuist.rhcloud.com/api/dcr/price")

    val request = GET(url)
    val response = Await.result(request.apply, 2.second)
    assert(response.bodyString.startsWith("{\n  \"usd\":"))
  }


  test("Most basic request with newman") {
    pending
    implicit val client = new ApacheHttpClient
    val response = Await.result(GET(url(http, "paypal.com")).apply, 5.second)
    assert(response.code.stringVal == "Ok")
  }


}
