package cn.itcast.controller;

import cn.itcast.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Date;
import java.util.Map;

/**
 * 常用的注解
 */
@Controller
@RequestMapping("/anno")
@SessionAttributes(value = {"msg"})
public class AnnoController {
    @RequestMapping("/testRequestParam")
    public String testRequestParam(@RequestParam(name = "name") String username){
        System.out.println("执行了");
        System.out.println(username);
        return "success";
    }

    /**
     * 获取到请求体的内容
     * @param body
     * @return
     */
    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String body){
        System.out.println("执行了");
        System.out.println(body);
        return "success";
    }

    /**
     * PathVariable注解
     * @param id
     * @return
     */
    @RequestMapping(value = "/testPathVariable/{sid}")
    public String testPathVariable(@PathVariable(name = "sid") String id){
        System.out.println("执行了");
        System.out.println(id);
        return "success";
    }

    /**
     * 获取请求头的值
     * @param header
     * @return
     */
    @RequestMapping("/testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "Accept") String header){
        System.out.println("执行了");
        System.out.println(header);
        return "success";
    }

    /**
     * 获取Cookie的值
     * @param cookieValue
     * @return
     */
    @RequestMapping("/testCookieValue")
    public String testCookieValue(@CookieValue(value = "JSESSIONID") String cookieValue){
        System.out.println("执行了");
        System.out.println(cookieValue);
        return "success";
    }


    /**
     * ModelAttribute
     * @param user
     * @return
     */
    @RequestMapping("/testModelAttribute")
    public String testModelAttribute(@ModelAttribute("abc") User user){
        System.out.println("testModelAttribute执行了");
        System.out.println(user);
        return "success";
    }

    /**
     * 无返回值传入Map
     * @param uname
     * @param map
     */
    @ModelAttribute
    public void showUser(String uname, Map<String,User> map){
        System.out.println("showUser执行了...");
        //通过用户查询数据库(模拟)
        User user = new User();
        user.setUname(uname);
        user.setAge(20);
        user.setDate(new Date());
        map.put("abc", user);
    }


    /**
     *有返回值
     * @param uname
     * @return

    @ModelAttribute
    public User showUser(String uname){
        System.out.println("showUser执行了...");
        //通过用户查询数据库(模拟)
        User user = new User();
        user.setUname(uname);
        user.setAge(20);
        user.setDate(new Date());
        return user;
    }
     */

    /**
     * SessionAttributes的注解
     * @param model
     * @return
     */
    @RequestMapping("/testSessionAttributes")
    public String testSessionAttributes(Model model){
        System.out.println("testSessionAttributes执行了");
        //底层会存储到request域对象当中
        model.addAttribute("msg", "美美");
        return "success";
    }

    /**
     * 获取session域中值
     * @param modelMap
     * @return
     */
    @RequestMapping("/getSessionAttributes")
    public String getSessionAttributes(ModelMap modelMap){
        System.out.println("getSessionAttributes执行了");
        //底层会存储到request域对象当中
        String msg = (String) modelMap.get("msg");
        System.out.println(msg);
        return "success";
    }

    /**
     * 清除
     * @param status
     * @return
     */
    @RequestMapping("/deleteSessionAttributes")
    public String deleteSessionAttributes(SessionStatus status){
        System.out.println("deleteSessionAttributes执行了");
        status.setComplete();
        return "success";
    }
}
