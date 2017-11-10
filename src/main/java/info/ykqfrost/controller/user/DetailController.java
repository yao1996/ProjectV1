package info.ykqfrost.controller.user;

import info.ykqfrost.beans.BookDetails;
import info.ykqfrost.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author YaoKeQi
 * @date 2017/10/24
 */
@Controller
public class DetailController {

    private BookService bookService;
    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/detail/{bookId}")
    public String detail(@PathVariable("bookId") String bookId, Model model) {
        int i = Integer.parseInt(bookId);
        BookDetails bookDetails = bookService.selectByTypeId(i);
        model.addAttribute("book", bookDetails);
        return "readerTemplates/detail";
    }
}
