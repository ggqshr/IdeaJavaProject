package Controller;

import Pojo.User;
import Service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.logging.Logger;

@Controller
public class testConnect {
    @Autowired
    private Gson gson;
    @Resource(name = "UserServiceImpl")
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/test", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String test(@RequestBody String body) {
        User user = gson.fromJson(body, User.class);
        return gson.toJson(userService.userLogin(user, getIpAddr(request)));
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String tt() {
        Logger logger = Logger.getLogger(String.valueOf(testConnect.class));
        logger.info("hello logger");
        return "ok";
    }

    /**
     * 获取请求的ip地址
     *
     * @param request 传入request对象
     * @return 返回ip地址
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }
}
