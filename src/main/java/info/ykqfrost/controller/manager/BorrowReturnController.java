package info.ykqfrost.controller.manager;

import info.ykqfrost.beans.LogBean;
import info.ykqfrost.service.BorrowReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;

/**
 * @author YaoKeQi
 * @date 2017/10/30
 */
@Controller
@RequestMapping("/manage")
public class BorrowReturnController {
    private boolean returnFlag = false;
    private boolean borrowFlag = false;
    private final BorrowReturnService borrowReturnService;

    @Autowired
    public BorrowReturnController(BorrowReturnService borrowReturnService) {
        this.borrowReturnService = borrowReturnService;
    }

    @GetMapping("/borrow")
    public String borrow(Model model, HttpSession session) {
        if (borrowFlag) {
            session.removeAttribute("isBorrowed");
        }
        borrowFlag = true;
        String isManager = "isManager";
        if (session.getAttribute(isManager) != null && session.getAttribute(isManager).equals(true)) {
            model.addAttribute("logBean",new LogBean());
            return "managerTemplates/borrowBooks";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/borrow/commit")
    public String borrowCommit(@Valid LogBean logBean,BindingResult bindingResult,HttpSession session){
        logBean.setBorrowDate(new Date());

        try {
            borrowReturnService.borrowBook(logBean);
        } catch (Exception e) {
            session.setAttribute("isBorrowed","false");
            borrowFlag = false;
        }
        return "redirect:/manage/borrow";
    }

    @GetMapping("/return")
    public String returnBook(Model model,HttpSession session) {
        if (returnFlag){
            session.removeAttribute("isReturned");
        }
        returnFlag = true;
        String isManager = "isManager";
        if (session.getAttribute(isManager) != null && session.getAttribute(isManager).equals(true)) {
            model.addAttribute("logBean",new LogBean());
            return "managerTemplates/returnBooks";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/return/commit")
    public String returnBookCommit(@Valid LogBean logBean, BindingResult bindingResult,HttpSession session) {
        logBean.setReturnDate(new Date());
        try {
            borrowReturnService.returnBook(logBean);
        } catch (Exception e) {
            session.setAttribute("isReturned","false");
            returnFlag = false;
        }
        return "redirect:/manage/return";
    }
}
