package ggq.Controller;

import ggq.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {
    //     1.浏览器客户端返回的都是json，不能自适应
//    @ResponseBody
//    @ExceptionHandler(UserNotExistException.class)
//    public Map<String, Object> handlerException(Exception e) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("code", "user.notexist");
//        map.put("message", e.getMessage());
//        return map;
//    }
    //转发到error，交给springboot进行自适应
    @ExceptionHandler(UserNotExistException.class)
    public String handlerException(Exception e, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //需要传入自己的错误代码 否则不会进入错误页面
        request.setAttribute("javax.servlet.error.status_code", 500);
        map.put("code", "user.notexist");
        map.put("message", e.getMessage());
        request.setAttribute("ext",map);
//        转发到/error
        return "forward:/error";
    }
}
