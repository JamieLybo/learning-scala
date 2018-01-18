package learningscala.base

import scala.actors.Actor

/**
  * Created by Administrator on 2018/1/18.
  */
class SendActor extends Actor {
  override def act() = {
    while (true) {
      receive {
        case Msg(content,response) => {
          println("== " + content)
        }
      }
    }
  }
}

class ReceiveActor(send:SendActor) extends Actor {
  override def act() = {
    while (true) {
      receive {
        case content:String => {
          println(s"..I get ${content}")
          send ! Msg("Got",this)
        }
      }
    }
  }
}

case class Msg(content: String, response: Actor)