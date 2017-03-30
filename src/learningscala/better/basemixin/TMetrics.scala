package learningscala.better.basemixin

import java.util.Date
import java.util.concurrent.TimeUnit

/**
  * it's a example for base mixin using trait
  */
trait TMetrics {
  def timeIt[T](desc: String)(f: () => T): T = {
    println(s"Thread:${Thread.currentThread().getId} | BEGIN | ${desc} | ${new Date()}")
    val res = f.apply()
    println(s"Thread:${Thread.currentThread().getId} | END   | ${desc} | ${new Date()}")
    res
  }
}

object MetricsTest extends TMetrics with App{
  timeIt("Test")(() => TimeUnit.SECONDS.sleep(3))
}
