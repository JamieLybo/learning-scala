package learningscala.pattern.loan

import java.io.File
import java.util.Scanner

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

}
