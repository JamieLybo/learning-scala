package learningscala.pattern.loan

import java.io.File
import java.util.{Properties, Scanner}

/**
  * Loan pattern to control resource generate, close
  * we just focus on how to use it
  */
object Loan extends App {
  def withScanner[T](file: File)(op: Scanner => T): T = {
    val scanner = new Scanner(file)
    try {
      op(scanner)
    } finally {
      scanner.close()
    }
  }

  withScanner(new File("tmp/test.txt")) { scanner =>
    while (scanner.hasNext)
      println(scanner.next())
  }

  //another loan
  def using[A <: {def close() : Unit}, B](resource: A)(f: A => B): B =
    try {
      f(resource)
    }
    finally {
      if (resource != null)
        resource.close()
    }

  def propToMap: Map[String, String] = {
    val prop = new Properties()
    using(this.getClass.getClassLoader.getResourceAsStream(s"prop.properties")) {
      source => {
        prop.load(source)
        import scala.collection.JavaConversions.propertiesAsScalaMap
        prop.toMap
      }
    }
  }

  println(propToMap)
}
