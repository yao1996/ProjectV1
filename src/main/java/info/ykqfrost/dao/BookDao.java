package info.ykqfrost.dao;

import info.ykqfrost.beans.Book;
import info.ykqfrost.beans.BookDetails;
import info.ykqfrost.beans.DeleteForm;
import java.util.ArrayList;

public interface BookDao {
    ArrayList<BookDetails> selectAllBooks();

    BookDetails selectByTypeId(int var1);

    BookDetails selectByIsbn13(long var1);

    String selectBookNameByBookId(int var1);

    Integer selectTypeIdByIsbn(long var1);

    Double selectPriceByBookId(int var1);

    Book selectBookByBookId(int var1);

    ArrayList<Integer> selectBookIdByTypeId(int var1);

    ArrayList<BookDetails> searchForBook(String var1);

    ArrayList<DeleteForm> selectDeleteRecords();

    int addBook(BookDetails var1);

    int addExisted(BookDetails var1);

    int addSpecificBook(Book var1);

    int selectOutsides(int var1);

    int deleteBooksByBookId(int var1);

    int deleteBooksByTypeId(int var1);

    int deleteBookByIsbn13(long var1);

    int deleteRecord(DeleteForm var1);

    void deleteOneBookOutsideByBookId(int var1);

    void deleteOneBookInsideByBookId(int var1);
}
