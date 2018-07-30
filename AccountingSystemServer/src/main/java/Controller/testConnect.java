package Controller;

import Pojo.User;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.logging.Logger;

@Controller
public class testConnect {
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public String test(@RequestBody String body) {
        return "ok";
////        Gson gson = new Gson();
////        System.out.println(body);
////        User user = gson.fromJson(body, User.class);
////        System.out.println(user.toString());
////        User user1 = new User();
////        user1.setPassword("123");
////        user1.setUsername("ggqqqq");
//        return gson.toJson(user1);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String tt() {
        Logger logger = Logger.getLogger(String.valueOf(testConnect.class));
        logger.info("hello logger");
        return "ok";
    }
}
