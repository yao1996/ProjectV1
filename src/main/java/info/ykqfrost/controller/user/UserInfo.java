package info.ykqfrost.controller.user;

import info.ykqfrost.beans.LogBean;
import info.ykqfrost.beans.Reader;
import info.ykqfrost.service.BorrowReturnService;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserInfo {
    private BorrowReturnService borrowReturnService;

    @Autowired
    public void setBorrowReturnService(BorrowReturnService borrowReturnService) {
        this.borrowReturnService = borrowReturnService;
    }

    @RequestMapping({"/userInfo"})
    public String userInfo(HttpSession session, Model model) {
        Reader reader = (Reader)session.getAttribute("user");
        if (reader == null) {
            return "redirect:/login";
        } else if ("admin".equals(reader.getUsername())) {
            return "redirect:/manage";
        } else {
            ArrayList<LogBean> logBeans = this.borrowReturnService.selectAllLogOne(reader.getUsername());
            model.addAttribute("allLogs", logBeans);
            model.addAttribute("all", "true");
            return "readerTemplates/user";
        }
    }

    @RequestMapping({"/userUnReturned"})
    public String userUnReturned(HttpSession session, Model model) {
        Reader reader = (Reader)session.getAttribute("user");
        if (reader == null) {
            return "redirect:/login";
        } else if ("admin".equals(reader.getUsername())) {
            return "redirect:/manage";
        } else {
            ArrayList<LogBean> logBeans = this.borrowReturnService.selectUnReturnedOne(reader.getUsername());
            model.addAttribute("unReturnedLogs", logBeans);
            model.addAttribute("all", "false");
            return "readerTemplates/user";
        }
    }
}
