package info.ykqfrost.service;

import info.ykqfrost.beans.Book;
import info.ykqfrost.beans.BookDetails;
import info.ykqfrost.beans.DeleteForm;
import info.ykqfrost.dao.BookDao;
import java.util.ArrayList;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    private BookDao bookDao;

    public BookService() {
    }

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public ArrayList<BookDetails> selectAllBooks() {
        return this.bookDao.selectAllBooks();
    }

    public BookDetails selectByTypeId(int typeId) {
        return this.bookDao.selectByTypeId(typeId);
    }

    BookDetails selectByIsbn13(long isbn13) {
        return this.bookDao.selectByIsbn13(isbn13);
    }

    public ArrayList<DeleteForm> selectDeleteRecords() {
        return this.bookDao.selectDeleteRecords();
    }

    public int selectTypeIdByIsbn(long isbn) {
        Integer i = this.bookDao.selectTypeIdByIsbn(isbn);
        return i == null ? 0 : i.intValue();
    }

    public double selectPriceByBookId(int bookId) {
        Double price = this.bookDao.selectPriceByBookId(bookId);
        return price == null ? 0.0D : price.doubleValue();
    }

    int addSpecificBook(Book book) {
        return this.bookDao.addSpecificBook(book);
    }

    int addBook(BookDetails bookDetails) {
        return this.bookDao.addBook(bookDetails);
    }

    public ArrayList<BookDetails> searchForBook(String search) {
        return this.bookDao.searchForBook(search);
    }

    int addExisted(BookDetails bookDetails) {
        return this.bookDao.addExisted(bookDetails);
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public void deleteBook(DeleteForm deleteForm) throws Exception {
        if ("byIsbn13".equals(deleteForm.getMethod())) {
            BookDetails bookDetails = this.selectByIsbn13(deleteForm.getIsbn13());
            if (bookDetails == null) {
                throw new Exception("there is no book whose isbn equals input !");
            }

            if (this.bookDao.selectOutsides(bookDetails.getTypeId()) > 0) {
                throw new Exception("some books are borrowed now,please delete after they are returned !");
            }

            if (this.bookDao.deleteBookByIsbn13(deleteForm.getIsbn13()) != 1) {
                throw new Exception("delete book error !");
            }

            ArrayList<Integer> list = this.bookDao.selectBookIdByTypeId(bookDetails.getTypeId());
            deleteForm.setBookName(bookDetails.getBookName());
            Iterator var4 = list.iterator();

            while(var4.hasNext()) {
                int i = ((Integer)var4.next()).intValue();
                deleteForm.setBookId(i);
                this.bookDao.deleteRecord(deleteForm);
            }

            this.bookDao.deleteBooksByTypeId(bookDetails.getTypeId());
        } else if ("byBookId".equals(deleteForm.getMethod())) {
            Book book = this.bookDao.selectBookByBookId(deleteForm.getBookId());
            if (book == null) {
                throw new Exception("there is no this book !");
            }

            BookDetails bookDetails = this.selectByTypeId(book.getTypeId());
            if (book.isBorrowed()) {
                throw new Exception("this book is being borrowed");
            }

            this.bookDao.deleteOneBookInsideByBookId(deleteForm.getBookId());
            deleteForm.setIsbn13(bookDetails.getIsbn13());
            deleteForm.setBookName(bookDetails.getBookName());
            this.bookDao.deleteRecord(deleteForm);
            this.bookDao.deleteBooksByBookId(deleteForm.getBookId());
            if (this.bookDao.selectByIsbn13(bookDetails.getIsbn13()).getTotalNum() == 0) {
                this.bookDao.deleteBookByIsbn13(bookDetails.getIsbn13());
            }
        }

    }
}
