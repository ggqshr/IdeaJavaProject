package Controller;

import Pojo.User;
import Service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.logging.Logger;

@Controller
public class testConnect {
    @Autowired
    private Gson gson;
    @Resource(name = "UserServiceImpl")
    private UserService userService;

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public String test(@RequestBody String body) {
        User user = gson.fromJson(body, User.class);
        return gson.toJson(userService.userLogin(user));
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String tt() {
        Logger logger = Logger.getLogger(String.valueOf(testConnect.class));
        logger.info("hello logger");
        return "ok";
    }

}
