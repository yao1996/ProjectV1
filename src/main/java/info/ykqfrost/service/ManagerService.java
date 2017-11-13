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
    private BookDao bookDao;
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
    public void addBook(BookDetails bookDetails) {
        int i = bookDao.selectByIsbn(bookDetails.getIsbn10());
        int j = bookDao.selectByIsbn(bookDetails.getIsbn13());
        if (i ==0 && j ==0) {
            bookDao.addBook(bookDetails);
        }
        int num = bookDetails.getTotalNum();
        while (num != 0) {
            bookDao.addSpecificBook(bookDetails.getTypeId());
            num --;
        }
    }

    public boolean deleteBookByTypeId(int bookId) {
        return bookDao.deleteBookByTypeId(bookId) == 1;
    }

    public boolean updateBookDetail(BookDetails bookDetails) {
        return managerDao.updateBookDetail(bookDetails) == 1;
    }

}
