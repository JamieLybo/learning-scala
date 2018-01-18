package learningscala.base

/**
  * Created by Administrator on 2018/1/18.
  */
object Client {
  def main(args: Array[String]): Unit = {
    val send = new SendActor
    val get = new ReceiveActor(send)

    send.start()
    get.start()

    get ! "hello"
  }
}
