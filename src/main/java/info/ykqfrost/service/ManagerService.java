package info.ykqfrost.service;

import info.ykqfrost.beans.BookDetails;
import info.ykqfrost.dao.ManagerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author YaoKeQi
 * @date 2017/10/30
 */
@Service
public class ManagerService {
    private ManagerDao managerDao;
    @Autowired
    public void setManagerDao(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    public ArrayList<BookDetails> selectAllBooks() {
        return managerDao.selectAllBooks();
    }

    public boolean addBook(BookDetails bookDetails) {
        return managerDao.addBook(bookDetails) == 1;
    }

    public boolean deleteBookByTypeId(int bookId) {
        return managerDao.deleteBookByTypeId(bookId) == 1;
    }

    public boolean updateBookDetail(BookDetails bookDetails) {
        return managerDao.updateBookDetail(bookDetails) == 1;
    }

}
