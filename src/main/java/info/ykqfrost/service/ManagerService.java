package info.ykqfrost.service;

import info.ykqfrost.beans.Book;
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
    public ArrayList<Object> addBook(BookDetails bookDetails) {
        ArrayList<Object> list = new ArrayList<>();
        int i = bookService.selectTypeIdByIsbn(bookDetails.getIsbn10());
        int j = bookService.selectTypeIdByIsbn(bookDetails.getIsbn13());
        boolean k = i==0 && j==0;
        if (k) {
            bookService.addBook(bookDetails);
            list.add(bookDetails.getLocation());
        }else {
            bookService.addExisted(bookDetails);
            list.add(bookService.selectByIsbn13(bookDetails.getIsbn13()).getLocation());
        }
        int num = bookDetails.getTotalNum();
        Book book = new Book();
        book.setTypeId(bookDetails.getTypeId());
        while (num != 0) {
            bookService.addSpecificBook(bookDetails.getTypeId());
            list.add(book.getBookId());
            num --;
        }
        return list;
    }

    public boolean updateBookDetail(BookDetails bookDetails) {
        return managerDao.updateBookDetail(bookDetails) == 1;
    }

}
