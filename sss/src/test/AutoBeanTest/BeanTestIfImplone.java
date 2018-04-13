package test.AutoBeanTest;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by ggq on 2018/1/16.
 */
@Order(value = 2)  //只对List有用
@Component
public class BeanTestIfImplone implements BeanTestIf{
}
