package info.ykqfrost.service;

import info.ykqfrost.beans.Book;
import info.ykqfrost.beans.BookDetails;
import info.ykqfrost.dao.ManagerDao;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManagerService {
    private ManagerDao managerDao;
    private BookService bookService;

    public ManagerService() {
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setManagerDao(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public ArrayList<Object> addBook(BookDetails bookDetails) {
        ArrayList<Object> list = new ArrayList();
        int i = this.bookService.selectTypeIdByIsbn(bookDetails.getIsbn10());
        int j = this.bookService.selectTypeIdByIsbn(bookDetails.getIsbn13());
        boolean k = i == 0 && j == 0;
        if (k) {
            this.bookService.addBook(bookDetails);
            list.add("add book '" + bookDetails.getBookName() + "' succeeded, please locate books at " + bookDetails.getLocation());
        } else {
            this.bookService.addExisted(bookDetails);
            bookDetails.setTypeId(j);
            list.add("add book '" + bookDetails.getBookName() + "' succeeded, book is existed,please locate books at " + this.bookService.selectByIsbn13(bookDetails.getIsbn13()).getLocation());
        }

        int num = bookDetails.getTotalNum();
        Book book = new Book();
        book.setTypeId(bookDetails.getTypeId());

        while(num != 0) {
            this.bookService.addSpecificBook(book);
            list.add(book.getBookId());
            --num;
        }

        return list;
    }

    public boolean updateBookDetail(BookDetails bookDetails) {
        return this.managerDao.updateBookDetail(bookDetails) == 1;
    }
}
