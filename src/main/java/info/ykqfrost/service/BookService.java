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

    public BookDetails selectByTypeId(int typeId) {
        return bookDao.selectByTypeId(typeId);
    }

    public int selectByIsbn(long isbn) {
        Integer i = bookDao.selectByIsbn(isbn);
        if (i ==null) {
            return 0;
        }else {
            return i;
        }
    }

    int addSpecificBook(int typeId) {
        return bookDao.addSpecificBook(typeId);
    }

    int addBook(BookDetails bookDetails) {
        return bookDao.addBook(bookDetails);
    }

    int deleteBookByTypeId(int typeId) {
        return bookDao.deleteBookByTypeId(typeId);
    }

    public ArrayList<BookDetails> searchForBook(String search){
        return bookDao.searchForBook(search);
    }

    int addExisted(BookDetails bookDetails) {
        return bookDao.addExisted(bookDetails);
    }

}
