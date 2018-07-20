package MyRpc

import java.util.UUID
import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.concurrent.Await
import scala.concurrent.duration._

class Worker extends Actor {
  var master: ActorSelection = _
  val workerId: String = UUID.randomUUID().toString
  val CHECK_INTERVAL = 10000

  //建立连接
  override def preStart(): Unit = {
    //跟master建立连接，并发送自己的信息
    master = context.actorSelection("akka.tcp://MasterSystem@127.0.0.1:8888/user/MyRpc.Master")
    master ! RegisterWorker(workerId, 2000, 2)
  }

  override def receive: Receive = {
    case RegistedWorker(masterUrl) => {
      println("a reply from master")
      println(masterUrl)
      import context.dispatcher
      //启动定时任务，发送心跳
      context.system.scheduler.schedule(0 millis, CHECK_INTERVAL millis, self, SendHeatBeat)
    }
    case SendHeatBeat => {
      println("send heartBeat to master")
      master ! HeartBeat(workerId)
    }
  }


}

object Worker {
  def main(args: Array[String]): Unit = {
    val host = "127.0.0.1"
    //args(0)
    val port = "8889"
    //args(1).toInt
    val configset: String =
      s"""
         |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname = "$host"
         |akka.remote.netty.tcp.port = "$port"
       """.stripMargin
    val config = ConfigFactory.parseString(configset)
    val wordSystem = ActorSystem("wordSystem", config)
    val work = wordSystem.actorOf(Props[Worker], "work")
    Await.ready(wordSystem.whenTerminated, Duration(10, TimeUnit.MINUTES))
  }
}
