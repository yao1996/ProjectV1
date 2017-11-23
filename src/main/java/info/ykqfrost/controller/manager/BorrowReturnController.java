package info.ykqfrost.controller.manager;

import info.ykqfrost.beans.LogBean;
import info.ykqfrost.service.BorrowReturnService;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
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
public class BorrowReturnController {
    private boolean returnFlag = false;
    private boolean borrowFlag = false;
    private final BorrowReturnService borrowReturnService;

    @Autowired
    public BorrowReturnController(BorrowReturnService borrowReturnService) {
        this.borrowReturnService = borrowReturnService;
    }

    @GetMapping({"/borrow"})
    public String borrow(Model model, HttpSession session) {
        if (this.borrowFlag) {
            session.removeAttribute("isBorrowed");
        }

        this.borrowFlag = true;
        String isManager = "IS_MANAGER";
        if (session.getAttribute(isManager) != null && session.getAttribute(isManager).equals(true)) {
            model.addAttribute("logBean", new LogBean());
            return "managerTemplates/borrowBooks";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping({"/borrow"})
    public String borrowCommit(@Valid LogBean logBean, BindingResult bindingResult, HttpSession session) {
        logBean.setBorrowDate(new Date());

        try {
            this.borrowReturnService.borrowBook(logBean);
            session.setAttribute("isBorrowed", "true");
        } catch (Exception var5) {
            session.setAttribute("isBorrowed", var5.getMessage());
        }

        this.borrowFlag = false;
        return "redirect:/manage/borrow";
    }

    @GetMapping({"/return"})
    public String returnBook(Model model, HttpSession session) {
        String isManager = "IS_MANAGER";
        if (session.getAttribute(isManager) != null && session.getAttribute(isManager).equals(true)) {
            if (this.returnFlag) {
                session.removeAttribute("isReturned");
            }

            this.returnFlag = true;
            model.addAttribute("logBean", new LogBean());
            return "managerTemplates/returnBooks";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping({"/return"})
    public String returnBookCommit(@Valid LogBean logBean, BindingResult bindingResult, HttpSession session) {
        logBean.setReturnDate(new Date());
        logBean.setType("normal");

        try {
            double lessMoney = this.borrowReturnService.returnBook(logBean);
            session.setAttribute("isReturned", "return succeeded , consume " + lessMoney + " yuan");
        } catch (Exception var6) {
            session.setAttribute("isReturned", var6.getMessage());
        }

        this.returnFlag = false;
        return "redirect:/manage/return";
    }

    @GetMapping({"/damage"})
    public String damage(@RequestParam String bookId, HttpSession session) {
        if (bookId != null && !Objects.equals(bookId.trim(), "")) {
            LogBean logBean = new LogBean();
            logBean.setType("damage");
            logBean.setBookId(Integer.parseInt(bookId));
            logBean.setReturnDate(new Date());

            try {
                double lessMoney = this.borrowReturnService.damageBook(logBean);
                session.setAttribute("isReturned", "submit succeeded , consume " + lessMoney + " yuan");
            } catch (Exception var6) {
                session.setAttribute("isReturned", var6.getMessage());
            }

            this.returnFlag = false;
            return "redirect:/manage/return";
        } else {
            session.setAttribute("isReturned", "book ID can not be null !");
            this.returnFlag = false;
            return "redirect:/manage/return";
        }
    }

    @GetMapping({"/borrowRecord"})
    public String borrowRecord(Model model, @RequestParam(required = false) String search) {
        ArrayList logBeans;
        if (search == null) {
            logBeans = this.borrowReturnService.selectAllLog();
            model.addAttribute("isSearch", "false");
        } else {
            logBeans = this.borrowReturnService.searchLog(search);
            model.addAttribute("isSearch", search);
        }

        model.addAttribute("records", logBeans);
        return "managerTemplates/borrowRecord";
    }

    @GetMapping({"/borrowUnReturnedRecord"})
    public String borrowUnReturnedRecord(Model model, @RequestParam(required = false) String search) {
        ArrayList logBeans;
        if (search == null) {
            logBeans = this.borrowReturnService.selectUnReturned();
            model.addAttribute("isSearch", "false");
        } else {
            logBeans = this.borrowReturnService.searchUnReturnedLog(search);
            model.addAttribute("isSearch", search);
        }

        model.addAttribute("records", logBeans);
        return "managerTemplates/borrowRecord";
    }
}
