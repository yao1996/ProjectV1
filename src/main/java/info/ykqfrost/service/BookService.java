package info.ykqfrost.service;

import info.ykqfrost.beans.BookDetails;
import info.ykqfrost.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author YaoKeQi
 * @date 2017/10/27
 */
@Service
public class BookService {
    private BookDao bookDao;
    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public ArrayList<BookDetails> selectAllBooks() {
        return bookDao.selectAllBooks();
    }

    public BookDetails selectByTypeId(int bookId) {
        return bookDao.selectByTypeId(bookId);
    }

    public ArrayList<BookDetails> searchForBook(String search){
        return bookDao.searchForBook(search);
    }

}
