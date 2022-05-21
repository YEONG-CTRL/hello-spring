package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") //http "GET" : matches url "/hello"
    public String hello(Model model) {
        model.addAttribute("data", "hello!"); // data is "key" in model
        return "hello"; //find 'hello.html' in resources/templates
    }

    @GetMapping("hello-mvc") // MVC
    public String helloMvc(@RequestParam(value = "name") String name, Model model) { //get param from outside
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // directly insert data into http body
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody  //with this annotation, spring conveys value into http body
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // but when value is object like here, by default, spring makes object's data into json and give it to webbrowser
    }

    static class Hello {
        private String name;

        public String getName() { // access to private name by using getter and setter
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
