package info.ykqfrost.controller.manager;

import com.dongxuexidu.douban4j.model.app.DoubanException;
import info.ykqfrost.beans.AddBookForm;
import info.ykqfrost.beans.Ajax;
import info.ykqfrost.beans.BookDetails;
import info.ykqfrost.service.BookService;
import info.ykqfrost.service.ManagerService;
import info.ykqfrost.utils.Douban;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/manage"})
public class AddBook {
    private BookService bookService;
    private ManagerService managerService;
    private boolean bookAddMessage;

    @Autowired
    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping({"/addBook"})
    public String addBook(HttpSession session, Model model) {
        if (!this.bookAddMessage) {
            session.removeAttribute("bookAddMessage");
        }
        this.bookAddMessage = false;
        model.addAttribute("book", new BookDetails());
        return "managerTemplates/addBooks";
    }

    @PostMapping({"/addBook"})
    public String addBookCommit(@Valid AddBookForm addBookForm, BindingResult bindingResult, HttpSession session, Model model) {
        BookDetails bookDetails;
        try {
            bookDetails = Douban.getBookDetail(addBookForm);
        } catch (DoubanException | IOException var7) {
            session.setAttribute("bookAddMessage", "Network Error");
            this.bookAddMessage = true;
            return "redirect:/manage/addBook";
        }

        ArrayList<Object> list = this.managerService.addBook(bookDetails);
        if (list == null) {
            session.setAttribute("bookAddMessage", "add book error !");
            this.bookAddMessage = true;
            return "redirect:/manage/addBook";
        } else {
            session.setAttribute("location", list.get(0));
            list.remove(0);
            model.addAttribute("list", list);
            return "managerTemplates/barcode";
        }
    }

    @GetMapping({"/ajax"})
    @ResponseBody
    public Ajax ajax(@RequestParam String isbn) {
        long isbn13 = Long.parseLong(isbn);
        AddBookForm addBookForm = new AddBookForm();
        addBookForm.setIsbn(isbn13);
        try {
            BookDetails bookDetails = Douban.getBookDetail(addBookForm);
            Ajax ajax = new Ajax();
            ajax.setAuthor(bookDetails.getAuthor());
            ajax.setBookName(bookDetails.getBookName());
            ajax.setPublisher(bookDetails.getPublisher());
            return ajax;
        } catch (DoubanException | IOException var7) {
            return null;
        }
    }
}
