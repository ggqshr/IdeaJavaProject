package ReflectTest.ReflectServiceImpl;

import ReflectTest.ReflectService.IBoss;

/**
 * Created by ggq on 2018/3/15.
 */
public class Boss implements IBoss{
    @Override
    public int yifu(String size) {
        System.err.println("天猫小强旗舰店，老板给客户发快递----衣服型号 "+size);
        return 50;
    }
}
