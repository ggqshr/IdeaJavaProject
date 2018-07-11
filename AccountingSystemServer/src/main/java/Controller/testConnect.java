package Controller;

import Pojo.User;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class testConnect {
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public String test(@RequestBody String body) {
        Gson gson = new Gson();
        System.out.println(body);
        User user = gson.fromJson(body, User.class);
        System.out.println(user.toString());
        return "ok";
    }
}
