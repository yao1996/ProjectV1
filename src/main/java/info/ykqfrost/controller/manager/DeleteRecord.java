package info.ykqfrost.controller.manager;

import info.ykqfrost.beans.DeleteForm;
import info.ykqfrost.service.BookService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/manage"})
public class DeleteRecord {
    private BookService bookService;

    public DeleteRecord() {
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping({"/deleteRecord"})
    public String deleteRecord(Model model) {
        ArrayList<DeleteForm> records = this.bookService.selectDeleteRecords();
        model.addAttribute("records", records);
        return "managerTemplates/deleteRecord";
    }
}
