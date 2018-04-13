package ReflectTest.ReflectAction;

import ReflectTest.ReflectService.IBoss;
import ReflectTest.ReflectServiceImpl.Boss;
import org.junit.Test;

/**
 * Created by ggq on 2018/3/15.
 */
public class SaleAction {
    @Test
    public void saleByBossSelf() {
        IBoss boss = new Boss();
        System.out.println("老板自营");
        int money = boss.yifu("xxl");
        System.out.println("衣服成交价 ： " + money);
    }
}
