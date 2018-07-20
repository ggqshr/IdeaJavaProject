package MyRpc

trait RemoteMessage extends Serializable

// worker->master 在不同进程间进行通信，需要继承序列化接口
case class RegisterWorker(id: String, memory: Int, cores: Int) extends RemoteMessage

//注册成功后返回给worker的master地址
case class RegistedWorker(masterUrl: String) extends RemoteMessage

//心跳包提醒
case object SendHeatBeat extends RemoteMessage

//心跳包
case class HeartBeat(id: String) extends RemoteMessage

//检测Word是否存活
case object CheckTimeOut extends RemoteMessage