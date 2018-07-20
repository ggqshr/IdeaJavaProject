package MyRpc

class WorkerInfo(val id: String, val memory: Int, cores: Int) {
//  上次心跳时间
  var lastHeartTime: Long = _

}
