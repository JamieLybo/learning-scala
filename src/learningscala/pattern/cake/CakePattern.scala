package learningscala.pattern.cake

/**
  * in scala we use self-type annotations with traits to implement dependency injection
  * the better one is cake pattern, I use Chinese to describe it
  * 每个服务都提供一个组件trait，该trait包含
  * 1）所有依赖的组件，都以self type描述
  * 2）描述服务接口的特质
  * 3）一个抽象的val，该val将被初始化成服务的一个实例
  * 4）可以有选择的包含服务接口的实现
  */

trait LoggerComponent{
//  2)
  trait Logger{
    def log(content:String)
  }
//  3)
  val logger:Logger
//  4)
  class InfoLogger() extends Logger{
    override def log(msg:String): Unit = println(s"[Info:] $msg")
  }
  class WarnLogger() extends Logger{
    override def log(msg:String): Unit = println(s"[Warn:] $msg")
  }

}

trait UserComponent {
//  1)
  self:LoggerComponent=>
//  2)
  trait User {
    def save()
  }
//  3)
  val user:User
//  4)
  class VipUser(vipLevel:String) extends  User{
    override def save(): Unit = logger.log(vipLevel)
  }
}

object Main extends LoggerComponent with UserComponent with App{
  override val user=new VipUser("A level")
  override val logger=new InfoLogger
  user.save()
}

