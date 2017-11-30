package info.ykqfrost.controller.manager;

import info.ykqfrost.beans.LogBean;
import info.ykqfrost.beans.Reader;
import info.ykqfrost.service.BorrowReturnService;
import info.ykqfrost.service.UserService;

import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/manage"})
public class User {
    private UserService userService;
    private BorrowReturnService borrowReturnService;
    private boolean rechargeFlag = false;

    @Autowired
    public void setBorrowReturnService(BorrowReturnService borrowReturnService) {
        this.borrowReturnService = borrowReturnService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/user"})
    public String user(Model model, @RequestParam(required = false) String search) {
        ArrayList<Reader> readers;
        if (search == null) {
            readers = this.userService.selectAll();
        } else {
            Reader reader = this.userService.selectOneById(search);
            readers = new ArrayList<>();
            if (reader == null) {
                return "managerTemplates/userManager";
            }
            readers.add(reader);
        }

        model.addAttribute("users", readers);
        return "managerTemplates/userManager";
    }

    @GetMapping({"/viewUser"})
    public String viewUser(@RequestParam String username, Model model, HttpSession session) {
        if (!this.rechargeFlag) {
            session.removeAttribute("recharge");
        }

        this.rechargeFlag = false;
        Reader reader = this.userService.selectOneById(username);
        if (reader == null) {
            return "managerTemplates/userManager";
        } else {
            ArrayList<LogBean> logBeans = this.borrowReturnService.selectAllLogOne(username);
            model.addAttribute("user", reader);
            model.addAttribute("allLogs", logBeans);
            model.addAttribute("all", "true");
            return "managerTemplates/viewUser";
        }
    }

    @GetMapping({"/viewUserUnReturned"})
    public String viewUserUnReturned(@RequestParam String username, Model model, HttpSession session) {
        if (!this.rechargeFlag) {
            session.removeAttribute("recharge");
        }

        this.rechargeFlag = false;
        Reader reader = this.userService.selectOneById(username);
        if (reader == null) {
            return "managerTemplates/userManager";
        } else {
            ArrayList<LogBean> logBeans = this.borrowReturnService.selectUnReturnedOne(username);
            model.addAttribute("user", reader);
            model.addAttribute("allLogs", logBeans);
            model.addAttribute("all", "false");
            return "managerTemplates/viewUser";
        }
    }

    @PostMapping({"/recharge"})
    public String recharge(@Valid Reader user, BindingResult bindingResult, HttpSession session) {
        if (user.getAccount() == 0.0D) {
            return "redirect:/manage/viewUser/?username=" + user.getUsername();
        } else {
            try {
                this.userService.recharge(user);
                session.setAttribute("recharge", "recharge succeeded !");
            } catch (Exception var5) {
                session.setAttribute("recharge", var5.getMessage());
            }

            this.rechargeFlag = true;
            return "redirect:/manage/viewUser/?username=" + user.getUsername();
        }
    }
}
