package br.com.sunflowerstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by victorjunior on 26/10/17.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("layout/dashboard");
        return mv;

    }

    /*@RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }*/

    /*
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }*/

}
