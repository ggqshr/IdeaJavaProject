package hadoopRpc;

public class MyNamenode implements ClientNamenodeProtocol {
    //模拟namenode的业务方法之一，查询元数据
    @Override
    public String getMetaData(String path) {
        return path + " : 3 - {BLK_1,BLK_2} .....";
    }

}
