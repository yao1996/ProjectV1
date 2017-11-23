package info.ykqfrost.controller.manager;

import info.ykqfrost.beans.DeleteForm;
import info.ykqfrost.service.BookService;
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
public class DeleteBook {
    private static final String IS_MANAGER = "IS_MANAGER";
    private boolean deleteFlag = false;
    private BookService bookService;

    public DeleteBook() {
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping({"/delete"})
    public String deleteBook(HttpSession session, Model model) {
        if (session.getAttribute("IS_MANAGER") != null && session.getAttribute("IS_MANAGER").equals(true)) {
            DeleteForm deleteForm = new DeleteForm();
            model.addAttribute("deleteForm", deleteForm);
            if (!this.deleteFlag) {
                session.removeAttribute("delete");
            }

            this.deleteFlag = false;
            return "managerTemplates/deleteBooks";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping({"/delete"})
    public String deleteBookCommit(HttpSession session, @Valid DeleteForm deleteForm, BindingResult bindingResult) {
        try {
            this.bookService.deleteBook(deleteForm);
            session.setAttribute("delete", "delete book succeeded !");
        } catch (Exception var5) {
            session.setAttribute("delete", var5.getMessage());
        }

        this.deleteFlag = true;
        return "redirect:/manage/delete";
    }
}
