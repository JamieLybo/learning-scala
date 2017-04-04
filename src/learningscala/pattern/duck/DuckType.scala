package learningscala.pattern.duck

/**
  * struct type in scala, we call it duck type
  *
  * someone said its performance is not good due to using reflection,refer to
  * http://www.draconianoverlord.com/2011/10/04/why-no-one-uses-scala-structural-typing
  * so please don't use it everywhere
  *
  */
object DuckType extends App {

  //  def duckShow(Duck: {def quack(): String; def swim(): String}) {
  //    println(duck.quack())
  //  }
  type Duck = {
    def quack(): String
  }

  def duckShow(duck: Duck) = {
    println(duck.quack())
  }

  class RealDuck {
    def quack() = "ga ga~"
  }

  class ToyDuck {
    def quack() = "wa wa~"
  }

  class Man {

  }

  //  they are ok
  duckShow(new RealDuck)
  duckShow(new ToyDuck)
  //  it's wrong, type mismatch
  //  duckShow(new Man)
}
