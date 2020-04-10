package cn.itcast.controller;

import cn.itcast.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 返回字符串
     * @param model
     * @return
     */
    @RequestMapping(value = "/testString")
    public String testString(Model model){
        System.out.println("testString方法执行了");
        //模拟从数据库中查询出User对象
        User user = new User();
        user.setUsername("jy");
        user.setPassword("123");
        user.setAge(20);
        //model对象
        model.addAttribute("user", user);
        return "success";
    }

    /**
     * void
     * 请求转发一次请求，请求路径不用编写项目名称
     */
    @RequestMapping(value = "/testVoid")
    public void testVoid(HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println("testVoid方法执行了");
        //编写请求转发的程序
        //request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request,response);

        //重定向
        //response.sendRedirect(request.getContextPath()+"/index.jsp");

        //设置中文乱码
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //直接响应
        response.getWriter().print("你好");

        return;
    }

    /**
     * ModelAndView
     * @return
     */
    @RequestMapping(value = "/testModelAndView")
    public ModelAndView testModelAndView(){
        //创建一个ModelAndView对象
        ModelAndView mv = new ModelAndView();
        System.out.println("testModelAndView方法执行了");
        //模拟从数据库中查询出User对象
        User user = new User();
        user.setUsername("cl");
        user.setPassword("007");
        user.setAge(20);
        //把User对象存储到mv对象中，并且会吧user对象存入到request对象
        mv.addObject("user",user);
        //跳转到哪个页面
        mv.setViewName("success");
        return mv;
    }

    /**
     * 使用关键字的方式进行转发或者重定向
     * @param
     * @return
     */
    @RequestMapping(value = "/testForwardOrRedirect")
    public String testForwardOrRedirect(){
        System.out.println("testForwardOrRedirect方法执行了");
        //请求转发
        //return "forward:/WEB-INF/pages/success.jsp";

        //重定向
        return "redirect:/index.jsp";
    }

    /**
     * 模拟异步请求响应
     * @return
     */
    @RequestMapping(value = "/testAjax")
    public @ResponseBody User testAjax(@RequestBody User user){
        System.out.println("testAjax方法执行了。。。");
        //客户端发送ajax的请求，传的是json字符串，后端把json字符串封装到user对象中
        System.out.println(user);
        //做响应,模拟查询数据库
        user.setUsername("haha");
        user.setAge(40);
        //做响应
        return user;
    }
}
