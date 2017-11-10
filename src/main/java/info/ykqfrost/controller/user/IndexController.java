package info.ykqfrost.controller.user;

import info.ykqfrost.beans.BookDetails;
import info.ykqfrost.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;


/**
 * @author YaoKeQi
 * @date 2017/10/24
 */
@Controller
public class IndexController {
    private BookService bookService;
    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = {"/","/index"})
    public String index(Model model,HttpSession session,
                        @RequestParam(required = false) String search,
                        @RequestParam(required = false) String type)
    {
        String exit = "exit";
        if (type !=null && type.equals(exit)){
            session.removeAttribute("user");
        }
        ArrayList<BookDetails> bookDetails;
        if (search == null) {
            bookDetails = bookService.selectAllBooks();
        }else {
            bookDetails = bookService.searchForBook(search);
        }
        model.addAttribute("books", bookDetails);
        return "readerTemplates/index";
    }

}
