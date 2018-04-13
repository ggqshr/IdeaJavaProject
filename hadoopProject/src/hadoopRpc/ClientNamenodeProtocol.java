package hadoopRpc;

/**
 * 业务接口，用于动态代理
 */
public interface ClientNamenodeProtocol {
    public static final long versionID =1L;

    public String getMetaData(String path);
}
