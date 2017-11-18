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
    public static final String IS_MANAGER = "IS_MANAGER";
    private ManagerService managerService;
    private final BookService bookService;

    @Autowired
    public BookManageController(ManagerService managerService, BookService bookService) {
        this.managerService = managerService;
        this.bookService = bookService;
    }


    @GetMapping("/modify")
    public String modify(HttpSession session,Model model,@RequestParam String typeId){
        if (session.getAttribute(IS_MANAGER) != null && session.getAttribute(IS_MANAGER).equals(true)) {
            int id = Integer.parseInt(typeId);
            BookDetails bookDetails = bookService.selectByTypeId(id);
            model.addAttribute("bookDetails", bookDetails);
            return "managerTemplates/changeBooks";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/modify")
    public String modifyCommit(@Valid BookDetails bookDetails, BindingResult bindingResult) {
        managerService.updateBookDetail(bookDetails);
        return "redirect:/manage/modify?bookId="+ bookDetails.getTypeId();
    }

}
