package learningscala.base

import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.TimeUnit

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object FutureTest {
  def main(args: Array[String]): Unit = {
    val results = Await.result(f4, 5 seconds)
    println("end: " + new SimpleDateFormat("hh:mm:ss:SSS").format(new Date()))
    println(results)
  }

  val f1 = Future {
    println("f1 executing " + new SimpleDateFormat("hh:mm:ss:SSS").format(new Date()))
    TimeUnit.SECONDS.sleep(1)
    "f1"
  }

  val f2 = Future {
    println("f2 executing " + new SimpleDateFormat("hh:mm:ss:SSS").format(new Date()))
    TimeUnit.SECONDS.sleep(2)
    "f2"
  }

  val f3 = Future {
    println("f3 executing " + new SimpleDateFormat("hh:mm:ss:SSS").format(new Date()))
    TimeUnit.SECONDS.sleep(3)
    "f3"
  }

  val f4: Future[Seq[String]] = Future.sequence(Seq(f1, f2, f3))
}
