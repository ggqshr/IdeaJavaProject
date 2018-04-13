package test.AutoBeanTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by ggq on 2018/1/16.
 */
@Component
public class TestAuto {
    @Autowired
    public List<BeanTestIf> list;
    @Autowired(/*required = true*/)
    private Map<String, BeanTestIf> map;

    public void say() {
        if (list != null) {
            for (BeanTestIf f :
                    list) {
                System.out.println(f.getClass().getName());

            }
        } else {
            System.out.println("this is null!!");
        }
    }
    public void sayMap()
    {
        if(map!=null)
        {
            for(Map.Entry<String,BeanTestIf> e : map.entrySet())
            {
                System.out.println(e.getKey()+"   "+e.getValue().getClass().getName());
            }
        }
    }
}
