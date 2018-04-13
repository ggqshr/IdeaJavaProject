package test.AutoBeanTest.BeanAnooTest;

/**
 * Created by ggq on 2018/1/16.
 */
public class StringStore implements Store {
    public void init()
    {
        System.out.println("this is init");
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
    }

    public void destroy()
    {
        System.out.println("this is destory");
    }
}
