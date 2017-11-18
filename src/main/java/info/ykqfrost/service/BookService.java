package info.ykqfrost.service;

import info.ykqfrost.beans.Book;
import info.ykqfrost.beans.BookDetails;
import info.ykqfrost.beans.DeleteForm;
import info.ykqfrost.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    BookDetails selectByIsbn13(long isbn13) {
        return bookDao.selectByIsbn13(isbn13);
    }

    public int selectTypeIdByIsbn(long isbn) {
        Integer i = bookDao.selectTypeIdByIsbn(isbn);
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


    public ArrayList<BookDetails> searchForBook(String search){
        return bookDao.searchForBook(search);
    }

    int addExisted(BookDetails bookDetails) {
        return bookDao.addExisted(bookDetails);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteBook(DeleteForm deleteForm) throws Exception {
        if ("byIsbn13".equals(deleteForm.getMethod())){
            BookDetails bookDetails = selectByIsbn13(deleteForm.getIsbn13());
            if (bookDetails == null) {
                throw new Exception();
            }
            if (bookDao.deleteBookByIsbn13(deleteForm.getIsbn13()) != 1) {
                throw new Exception("delete book error !");
            }
            ArrayList<Integer> list = bookDao.selectBookIdByTypeId(bookDetails.getTypeId());
            deleteForm.setBookName(bookDetails.getBookName());
            for (int i : list) {
                deleteForm.setBookId(i);
                bookDao.deleteRecord(deleteForm);
            }
            bookDao.deleteBooksByTypeId(bookDetails.getTypeId());
        }else if ("byBookId".equals(deleteForm.getMethod())){
            Book book = bookDao.selectBookByBookId(deleteForm.getBookId());
            BookDetails bookDetails = selectByTypeId(book.getTypeId());
            if (book.isBorrowed()){
                bookDao.deleteOneBookOutsideByBookId(deleteForm.getBookId());
            }else {
                bookDao.deleteOneBookInsideByBookId(deleteForm.getBookId());
            }
            deleteForm.setIsbn13(bookDetails.getIsbn13());
            deleteForm.setBookName(bookDetails.getBookName());
            bookDao.deleteRecord(deleteForm);
            bookDao.deleteBooksByBookId(deleteForm.getBookId());
            if (bookDao.selectByIsbn13(bookDetails.getIsbn13()).getTotalNum() == 0) {
                bookDao.deleteBookByIsbn13(bookDetails.getIsbn13());
            }
        }
    }

}
