package learningscala.better.implicitmixin

/**
  * sometimes we can use implicit to make mixin more easier
  */
trait StringHelper {
  implicit def StringHelper(originalString: String) = new {
    def appendString(suffix: String) = originalString + "_" + suffix

    def appendLen(len: Int) = originalString + "_" + len
  }
}

object StringHandle extends StringHelper with App {
  //  implicit def test(s: String): Object = new {
  //    x: Int =>
  //    x + 1
  //  }

  val source = "sourceString"
  println("==test appendSuffix==")
  println(source.appendString("suffix"))
  println("==test appendLen==")
  println(source.appendLen(4))
}
