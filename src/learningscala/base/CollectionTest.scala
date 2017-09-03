package learningscala.base

/**
  * collection功能测试，包括Array、Seq、Map
  *
  * 选择使用Seq时，若需要索引下标功能，优先考虑选择Vector，若需要Mutable的集合，则选择ArrayBuffer；
  * 若要选择Linear集合，优先选择List，若需要Mutable的集合，则选择ListBuffer
  * 有序增加用+：或：+，无序增加用+，增加一个元素用+，增加多个元素用++
  *
  */
object ArrayTest {
  //Arrays are mutable, indexed collections of values
  //array初始化
  val arrayIn0 = new Array[String](10)
  val arrayIn1 = Array("a", "b", 3)
  /*
  若长度固定则使用Array，若长度可能有变化则使用ArrayBuffer
  提供初始值时不要使用new
  用()来访问元素
  用for(elem<-arr)来遍历元素
  用for(elem<-arr if...) yield...（即for推导式）来将原数组转型为新数组
   */
}

object SeqTest extends App {
  //sequences always have a defined order of elements
  //seq初始化
  val seq0 = Seq(1, 2, 3, 4, 5, 6, 7, 8, 9, 10) //seq是一个trait，不能new
  val seq=seq0:+11
  //推导式,加if守卫
  //不需要下标
  val seq1 = for (ele <- seq0 if (ele % 2 == 0)) yield {
    ele
  }
  //需要下标
  val seq2 = for (i <- seq0.indices) yield {
    if (seq0(i) % 2 == 0)
      seq0(i)
    else
      0
  }
  //有时yild很难描述(比如使用case处理异常值时)，就用mutable吧
  import scala.collection.mutable._
  var mutSeq = new ArrayBuffer[Int]()
  for (i <- seq0.indices) yield {
    seq0(i) % 3 match {
      case 1 => mutSeq += seq0(i)
      case 2 => mutSeq += seq0(i)
      case _ =>
    }
  }
  seq1.foreach(println)
  println("-------")
  seq2.foreach(println)
  println("-------")
  mutSeq.foreach(println)
  println("-------")
  //遍历集合对象时，如果需要获得并操作集合对象的下标
  for ((number, index) <- seq0.zipWithIndex) yield (index, number)
  //foldleft
  val seq3 = seq0.foldLeft("0")((result: String, current: Int) => {
    result + current
  })
  println(seq3)
  println("-------")
  //zip
  val seq4=seq1.zip(seq2)
  seq4.foreach(println)
  println("-------")
}

object MapTest {
  //映射是由对偶构成的，映射是对偶的集合
  //map初始化
  val mapIn0 = Map[String, Int]()
  //map是trait，hashmap是class
  //map增加元素，变成新的map
  val map1 = mapIn0 + ("a" -> 10, "b" -> 20)
  val mapNew = map1 ++ Map(("c", 30))
  //在遍历Map对象或者Tuple的List时，且需要访问map的key和value值时，优先考虑采用Partial Function或者for循环，而非使用_1和_2的形式
  mapNew.foreach { case (name, age) => println(s"$name:$age") }
  for ((name, age) <- mapNew) println(s"$name:$age")
  //获取key对应value
  val value: Int = mapNew.getOrElse("a", 0)
  val value1: Int = mapNew("b")
}
