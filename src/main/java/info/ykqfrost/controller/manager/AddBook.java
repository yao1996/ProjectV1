package info.ykqfrost.controller.manager;

import com.dongxuexidu.douban4j.model.app.DoubanException;
import info.ykqfrost.beans.AddBookForm;
import info.ykqfrost.beans.BookDetails;
import info.ykqfrost.service.BookService;
import info.ykqfrost.service.ManagerService;
import info.ykqfrost.utils.Douban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

/**
 * @author YaoKeQi
 * @date 2017/11/16
 */
@Controller
@RequestMapping("/manage")
public class AddBook {
    private BookService bookService;
    private ManagerService managerService;
    private static final String ISMANAGER = "isManager";
    private boolean addBookException;

    @Autowired
    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/addBook")
    public String addBook(HttpSession session, Model model) {
        if (session.getAttribute(ISMANAGER) != null && session.getAttribute(ISMANAGER).equals(true)) {
            if (!addBookException) {
                session.removeAttribute("addBookException");
            }
            addBookException = false;
            model.addAttribute("book", new BookDetails());
            return "managerTemplates/addBooks";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/addBook")
    public String addBookCommit(@Valid AddBookForm addBookForm, BindingResult bindingResult
                                 , HttpSession session){
        BookDetails bookDetails;
        try {
            bookDetails = Douban.getBookDetail(addBookForm);
        } catch (IOException | DoubanException e) {
            session.setAttribute("addBookException","Network Error !");
            addBookException = true;
            return "redirect:/manage/addBook";
        }
        managerService.addBook(bookDetails);
        return "redirect:/manage/addBook";
    }

}
