package cn.moling.spacet.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * @Auther: zhanglk
 * @Date: 2019/4/9 09:36
 * @Description:
 */
@RestController
public class LoginController {

    @RequestMapping("/login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("/login.html");

        return mv;
    }

    @RequestMapping("/register")
    public ModelAndView register(){
        ModelAndView mv = new ModelAndView("/register.html");

        return mv;
    }

    @RequestMapping("/index")
    public ModelAndView index(Authentication authResult, Principal principal){
//        String userCode = principal.getName();
        ModelAndView mv = new ModelAndView("/index.html");
//        JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
//        mv.addObject("userName", jwtUser.getUsername());
//        mv.addObject("role", jwtUser.getAuthorities());
        return mv;
    }
}
