package info.ykqfrost.controller.manager;

import info.ykqfrost.service.BookService;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ManageIndex {
    private BookService bookService;

    public ManageIndex() {
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping({"/manage"})
    public String index(HttpSession session, Model model, @RequestParam(required = false) String type, @RequestParam(required = false) String search) {
        String exit = "exit";
        if (type != null && type.equals(exit)) {
            session.removeAttribute("IS_MANAGER");
        }

        String isManager = "IS_MANAGER";
        if (session.getAttribute(isManager) != null && session.getAttribute(isManager).equals(true)) {
            ArrayList bookDetailses;
            if (search == null) {
                bookDetailses = this.bookService.selectAllBooks();
            } else {
                bookDetailses = this.bookService.searchForBook(search);
            }

            model.addAttribute("bookDetailses", bookDetailses);
            return "managerTemplates/manageBook";
        } else {
            return "redirect:/login";
        }
    }
}
