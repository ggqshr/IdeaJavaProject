package ReflectTest.ReflectProxyclass;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by ggq on 2018/3/15.
 */
public class ProxyBoss {
    @SuppressWarnings("unchecked")
    public static <T> T getProxy(final int discountCoupon,
                                 final Class<?> interfaceClass,
                                 final Class<?> implementClass)
    {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                new Class[]{interfaceClass}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Integer returnValue = (Integer) method.invoke(implementClass.newInstance(),args);
                        return returnValue-discountCoupon;
                    }
                });
    }
}
