package test.AutoBeanTest;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by ggq on 2018/1/16.
 */
@Order(value = 1)
@Component
public class BeanTestIfImplTwo implements BeanTestIf {
}
