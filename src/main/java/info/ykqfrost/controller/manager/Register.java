package info.ykqfrost.controller.manager;

import info.ykqfrost.beans.Reader;
import info.ykqfrost.service.UserService;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/manage"})
public class Register {
    private boolean remove = false;
    private UserService userService;

    public Register() {
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/register"})
    public String register(Model model, HttpSession session) {
        if (!this.remove) {
            session.removeAttribute("isRegistered");
        }

        this.remove = false;
        model.addAttribute("reader", new Reader());
        return "managerTemplates/addUser";
    }

    @PostMapping({"/register"})
    public String registerCommit(@Valid Reader reader, BindingResult bindingResult, HttpSession session) {
        try {
            this.userService.register(reader);
            session.setAttribute("isRegistered", "true");
        } catch (Exception var5) {
            session.setAttribute("isRegistered", "false");
        }

        this.remove = true;
        return "redirect:/manage/register";
    }
}
