package ReflectTest.ReflectAction;

import ReflectTest.ReflectProxyclass.ProxyBoss;
import ReflectTest.ReflectService.IBoss;
import ReflectTest.ReflectServiceImpl.Boss;
import org.junit.Test;

/**
 * Created by ggq on 2018/3/15.
 */
public class ProxySaleAction {
    @Test
    public void saleByProxy(){
        IBoss boss = ProxyBoss.getProxy(10,IBoss.class, Boss.class);
        System.out.println("代理经营");
        int money = boss.yifu("xxl");
        System.out.println("衣服成交价  ："+money);
    }
}
