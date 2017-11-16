package info.ykqfrost.service;

import info.ykqfrost.beans.BookDetails;
import info.ykqfrost.dao.BookDao;
import info.ykqfrost.dao.ManagerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @author YaoKeQi
 * @date 2017/10/30
 */
@Service
public class ManagerService {
    private ManagerDao managerDao;
    private BookService bookService;
    private BookDao bookDao;
    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
    @Autowired
    public void setManagerDao(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    public ArrayList<BookDetails> selectAllBooks() {
        return managerDao.selectAllBooks();
    }

    @Transactional(rollbackFor = Exception.class)
    public String addBook(BookDetails bookDetails) {
        int i = bookService.selectByIsbn(bookDetails.getIsbn10());
        int j = bookService.selectByIsbn(bookDetails.getIsbn13());
        boolean k = i==0 && j==0;
        if (k) {
            bookService.addBook(bookDetails);
        }else {
            bookService.addExisted(bookDetails);
        }
        int num = bookDetails.getTotalNum();
        while (num != 0) {
            bookService.addSpecificBook(bookDetails.getTypeId());
            num --;
        }
        if (k) {
            return null;
        }else{
            return bookService.selectByTypeId(bookDetails.getTypeId()).getLocation();
        }
    }

    public boolean deleteBookByTypeId(int bookId) {
        return bookDao.deleteBookByTypeId(bookId) == 1;
    }

    public boolean updateBookDetail(BookDetails bookDetails) {
        return managerDao.updateBookDetail(bookDetails) == 1;
    }

}
