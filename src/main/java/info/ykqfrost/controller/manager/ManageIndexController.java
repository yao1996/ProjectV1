package info.ykqfrost.controller.manager;

import info.ykqfrost.beans.BookDetails;
import info.ykqfrost.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * @author YaoKeQi
 * @date 2017/10/30
 */
@Controller
public class ManageIndexController {
    private final ManagerService service;
    @Autowired
    public ManageIndexController(ManagerService service) {
        this.service = service;
    }

    @GetMapping("/manage")
    public String index(HttpSession session, Model model, @RequestParam(required = false) String type) {
        String exit = "exit";
        if (type != null && type.equals(exit)){
            session.removeAttribute("isManager");
        }
        String isManager = "isManager";
        if (session.getAttribute(isManager) != null && session.getAttribute(isManager).equals(true)) {
            ArrayList<BookDetails> bookDetailses = service.selectAllBooks();
            model.addAttribute("bookDetailses", bookDetailses);
            return "managerTemplates/manageBook";
        } else {
            return "redirect:/login";
        }
    }
}
