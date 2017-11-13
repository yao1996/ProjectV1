package info.ykqfrost.controller.user;

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
public class RegisterController {
    private boolean remove = false;
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model,HttpSession session) {
        if (remove) {
            session.removeAttribute("isRegistered");
        }
        remove = true;
        model.addAttribute("reader",new Reader());
        return "readerTemplates/register";
    }

    @PostMapping("/register/commit")
    public String registerCommit(@Valid Reader reader, BindingResult bindingResult, HttpSession session){
        try {
            userService.register(reader);
            return "redirect:/login";
        } catch (Exception e) {
            session.setAttribute("isRegistered","false");
            remove = false;
            return "redirect:/register";

        }
    }
}
