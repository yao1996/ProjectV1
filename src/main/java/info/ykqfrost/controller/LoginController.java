package info.ykqfrost.controller;

import info.ykqfrost.beans.LoginForm;
import info.ykqfrost.beans.Reader;
import info.ykqfrost.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author YaoKeQi
 * @date 2017/10/24
 */
@Controller
public class LoginController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(Model model,HttpSession session) {
        session.removeAttribute("user");
        model.addAttribute("form",new LoginForm());
        return "readerTemplates/login";
    }

    @PostMapping("/login/commit")
    public String loginCommit(@Valid LoginForm form, BindingResult bindingResult, HttpSession session) {
        Reader reader = userService.login(form);
        if (reader == null) {
            session.setAttribute("user","false");
            return "redirect:/login";
        }else {
            session.setAttribute("user", reader);
            String admin = "admin";
            if (admin.equals(form.getAuthority())){
                session.setAttribute("isManager",true);
                return "redirect:/manage";
            }
            return "redirect:/";
        }
    }
}
