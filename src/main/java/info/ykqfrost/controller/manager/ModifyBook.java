package info.ykqfrost.controller.manager;

import info.ykqfrost.beans.BookDetails;
import info.ykqfrost.service.BookService;
import info.ykqfrost.service.ManagerService;
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
public class ModifyBook {
    static final String IS_MANAGER = "IS_MANAGER";
    private boolean modifyFlag = false;
    private ManagerService managerService;
    private final BookService bookService;

    @Autowired
    public ModifyBook(ManagerService managerService, BookService bookService) {
        this.managerService = managerService;
        this.bookService = bookService;
    }

    @GetMapping({"/modify"})
    public String modify(HttpSession session, Model model, @RequestParam String typeId) {
        if (session.getAttribute("IS_MANAGER") != null && session.getAttribute("IS_MANAGER").equals(true)) {
            if (!this.modifyFlag) {
                session.removeAttribute("modifyMessage");
            }

            this.modifyFlag = false;
            int id = Integer.parseInt(typeId);
            BookDetails bookDetails = this.bookService.selectByTypeId(id);
            model.addAttribute("bookDetails", bookDetails);
            return "managerTemplates/modifyBook";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping({"/modify"})
    public String modifyCommit(@Valid BookDetails bookDetails, BindingResult bindingResult, HttpSession session) {
        if (!this.managerService.updateBookDetail(bookDetails)) {
            session.setAttribute("modifyMessage", "modify failed");
        } else {
            session.setAttribute("modifyMessage", "modify succeeded");
        }

        this.modifyFlag = true;
        return "redirect:/manage/modify?typeId=" + bookDetails.getTypeId();
    }
}
