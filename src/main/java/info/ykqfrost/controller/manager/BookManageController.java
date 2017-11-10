package info.ykqfrost.controller.manager;

import info.ykqfrost.beans.BookDetails;
import info.ykqfrost.service.BookService;
import info.ykqfrost.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author YaoKeQi
 * @date 2017/10/30
 */
@Controller
@RequestMapping("/manage")
public class BookManageController {
    private String isManager = "isManager";
    private ManagerService service;
    private final BookService bookService;

    @Autowired
    public BookManageController(ManagerService service, BookService bookService) {
        this.service = service;
        this.bookService = bookService;
    }

    @GetMapping("/addBook")
    public String addBook(HttpSession session, Model model) {
        if (session.getAttribute(isManager) != null && session.getAttribute(isManager).equals(true)) {
            model.addAttribute("book", new BookDetails());
            return "managerTemplates/addBooks";
        } else {
            return "redirect:/login";
        }
    }
    @PostMapping("/addBook/commit")
    public String addBookCommit(@Valid BookDetails bookDetails, BindingResult bindingResult){
        service.addBook(bookDetails);
        return "redirect:/manage";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam String bookId,HttpSession session){
        if (session.getAttribute(isManager) != null && session.getAttribute(isManager).equals(true)) {
            service.deleteBookByBookId(Integer.parseInt(bookId));
            return "redirect:/manage";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/modify")
    public String modify(HttpSession session,Model model,@RequestParam String bookId){
        if (session.getAttribute(isManager) != null && session.getAttribute(isManager).equals(true)) {
            int bookID = Integer.parseInt(bookId);
            BookDetails bookDetails = bookService.selectByBookId(bookID);
            model.addAttribute("book", bookDetails);
            return "managerTemplates/changeBooks";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/modify/commit")
    public String modifyCommit(@Valid BookDetails bookDetails, BindingResult bindingResult) {
        service.updateBook(bookDetails);
        return "redirect:/manage/modify?bookId="+ bookDetails.getTypeId();
    }

}
