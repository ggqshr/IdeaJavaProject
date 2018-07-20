package MyRpc

import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.collection.mutable
import scala.concurrent.Await
import scala.concurrent.duration._

class Master(val host: String, val port: Int) extends Actor {
  println("constructor invoker")
  //Id和WorkerInfo的对应关系
  val id2Worker = new mutable.HashMap[String, WorkerInfo]()
  //保存所有的worker信息，set便于排序
  val workers = new mutable.HashSet[WorkerInfo]()
  //超时检测的间隔
  val CHECK_INTERVAL = 12000

  override def preStart(): Unit = {
    println("pre Start invoked")
    //导入隐式转换
    import context.dispatcher
    context.system.scheduler.schedule(0 millis, CHECK_INTERVAL millis, self, CheckTimeOut)
  }

  override def receive: Receive = {
    case RegisterWorker(id, memory, cores) => {
      //判断是否注册
      if (!id2Worker.contains(id)) //如果不包含
      {
        val workerInfo = new WorkerInfo(id, memory, cores)
        id2Worker(id) = workerInfo
        workers += workerInfo
        sender ! RegistedWorker(s"akka.tcp://MasterSystem@$host:$port/user/MyRpc.Master")
      }
    }
    case HeartBeat(id) => {
      if (id2Worker.contains(id)) {
        val workerInfo = id2Worker(id)
        //报活
        val currentTime = System.currentTimeMillis()
        //更新最后心跳时间
        workerInfo.lastHeartTime = currentTime
      }
    }
    case CheckTimeOut => {
      //获得当前时间
      val currentTime = System.currentTimeMillis()
      //去除已经挂掉的机器
      val toRemove = workers.filter(x => currentTime - x.lastHeartTime > CHECK_INTERVAL)
      //从集合中移除这些work
      for (w <- toRemove) {
        workers -= w
        id2Worker -= w.id
      }
      println(workers.size)
    }
  }
}

object Master {


  def main(args: Array[String]): Unit = {
    val host = args(0)
    val port = args(1).toInt
    val configset: String =
      s"""
         |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname = "$host"
         |akka.remote.netty.tcp.port = "$port"
       """.stripMargin
    val config = ConfigFactory.parseString(configset)
    //ActorSystem是老大，负责创建和监控下面的actor，且他是单例
    val actorSystem = ActorSystem("MasterSystem", config)
    val master = actorSystem.actorOf(Props(new Master(host, port)), "MyRpc.Master")
    master ! "hello"
    Await.ready(actorSystem.whenTerminated, Duration(10, TimeUnit.MINUTES))
  }
}