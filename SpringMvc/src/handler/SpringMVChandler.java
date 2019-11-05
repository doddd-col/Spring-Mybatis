package handler;

import entity.Address;
import entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@SessionAttributes(value = {"student4","student2"})//将request域中的对象放到session中 可以放多个
@Controller
@RequestMapping("handler")
public class SpringMVChandler {
    @RequestMapping(value="welcome")//映射  可以控制访问的条件
    public String welcome(){
        return "success";
    }


    //REST风格 软件编程风格
    @RequestMapping(value="testget",method=RequestMethod.GET)
     public String testget(){

        System.out.println("testget");
        return "success";

     }

     @RequestMapping(value = "testpost",method = RequestMethod.POST)
    public String testpost(){

         System.out.println("testpost");
        return "success";
     }

     //    <input type="hidden" name="_method" value="DELETE" />  如果请求方式要变成DELETE或者PUT 需要加隐藏域  且提交方式是POST 才回将post改成DELETE|PUT
    @RequestMapping(value = "testdelete",method = RequestMethod.DELETE)
    public String testdelete(){

        System.out.println("testdelete");
        return "success";
    }

    @RequestMapping(value = "testput/{string}",method = RequestMethod.PUT)
    public String testput(@PathVariable("string") String s){

        System.out.println("testput"+s);
        return "success";
    }

    @RequestMapping(value = "testParam",method = RequestMethod.POST)  //如果服务端需要接受两个值 但是客服端只传来一个  可以用required = false
    public String testParam(@RequestParam("uname") String s,@RequestParam(value = "age",required = false,defaultValue = "23") int a){
        System.out.println("testParam"+s+a);
        return "success";
    }

    @RequestMapping(value = "testRequestHeader")
    //获取请求头信息
    public String testRequestHeader(@RequestHeader("Accept") String s){
        System.out.println(s);
        return "success";
    }

    @RequestMapping(value = "testCookieValue")
    //获取CookieValue
    public String testCookieValue(@CookieValue("JSESSIONID") String s){
        System.out.println("JSESSIONID:"+s);
        return "success";
    }

    @RequestMapping("testObjectProperties")
    public String testObjectProperties(Student student){
        System.out.println(student);
        return "success";
    }

    @RequestMapping("testModelAndView")
    public ModelAndView testModelAndView(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("success");
        Address address = new Address("北京","西安");
        Student student = new Student(1,"ls",address);

        //addObject 是key value对
        mv.addObject("student",student);//相当于request.setAttribute
        return mv;
    }

    @RequestMapping("testModelMap")
    public String testModelMap(ModelMap m){
        Address address = new Address("北京","西安");
        Student student2 = new Student(1,"ls",address);
        m.addAttribute("student2",student2);

        return "success";
    }

    @RequestMapping("testMap")
    public String testMap(Map<String,Object> m){
        Address address = new Address("北京","西安");
        Student student3 = new Student(1,"ls",address);
        m.put("student3",student3);

        return "success";
    }

    @RequestMapping("testModel")
    public String testModel(Model m){
        Address address = new Address("北京","西安");
        Student student4 = new Student(1,"ls",address);
        m.addAttribute("student4",student4);

        return "success";
    }



    @ModelAttribute//ModelAttribute思想 一个servlet对应一个功能  利用ModelAttribute可以插入一些功能
    public void query(Map<String,Object> map){
        Address address = new Address("北京","西安");
        Student student0 = new Student(3,"zs",address);
        map.put("student",student0);//约定：map的key 就是方法参数 类型的首字母小写
    }

    //修改  如果有ModelAttribute  每次执行方法前 都先执行它
    @RequestMapping("testModelAttribute")
    public String testModelAttribute(Student student1){//如果不一样  用@ModelAttribute("xxx")进行匹配

        System.out.println(student1);
        return "success";
    }

}
