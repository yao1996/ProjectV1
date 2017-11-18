package info.ykqfrost.dao;

import info.ykqfrost.beans.Book;
import info.ykqfrost.beans.BookDetails;
import info.ykqfrost.beans.DeleteForm;

import java.util.ArrayList;

/**
 * @author YaoKeQi
 * @date 2017/10/27
 */
public interface BookDao {
    /**
     * 取出所有书
     * @return list of all the books
     */
    ArrayList<BookDetails> selectAllBooks();

    /**
     * 通过编号查找书
     * @param bookId 图书编号
     * @return 详细信息
     */
    BookDetails selectByTypeId(int bookId);

    /**
     * 通过isbn查找书
     * @param isbn13 isbn13
     * @return 详细信息
     */
    BookDetails selectByIsbn13(long isbn13);

    /**
     * 通过isbn查找书，返回书籍类型编号
     * @param isbn 图书编号
     * @return 书籍类型编号
     */
    Integer selectTypeIdByIsbn(long isbn);

    /**
     * 从books中通过bookId返回typeId
     * @param bookId bookId
     * @return typeId
     */
    Book selectBookByBookId(int bookId);

    /**
     * 从books表中查询某类书的所有bookId
     * @param typeId typeId
     * @return bookId列表
     */
    ArrayList<Integer> selectBookIdByTypeId (int typeId);

    /**
     * 通过作者或书名搜索书
     * @param search 书名或作者名的一部分
     * @return 搜索到的书
     */
    ArrayList<BookDetails> searchForBook(String search);

    /**
     * 添加新书
     * @param bookDetails 书籍信息
     * @return 更新的行数
     */
    int addBook(BookDetails bookDetails);

    /**
     * 添加书籍时如果图书已存在几本，则在totalNum和remainNum上增加数量
     * @param bookDetails book
     * @return 更新的行数
     */
    int addExisted(BookDetails bookDetails);

    /**
     * 添加新书，自动生成每本书id
     * @param typeId 书籍类型编号
     * @return 更新的行数
     */
    int addSpecificBook(int typeId);

    /**
     * 通过书的id删除一本书，从数据库books中删除
     * @param bookID 图书id
     * @return 成功返回1
     */
    int deleteBooksByBookId(int bookID);

    /**
     * 通过typeId删除一类书，从数据库books中删除
     * @param typeId typeId
     * @return 返回该类书的数量
     */
    int deleteBooksByTypeId(int typeId);

    /**
     * 通过isbn13删除一类书，从数据库bookDetails中删除
     * @param isbn13 isbn13
     * @return  成功返回1
     */
    int deleteBookByIsbn13(long isbn13);

    /**
     * 添加删除图书记录
     * @param deleteForm deleteForm From Html
     * @return 成功返回1
     */
    int deleteRecord(DeleteForm deleteForm);

    /**
     * 删除具体某一本书时，如果书已外借，最大数量减一
     * @param bookId bookId
     */
    void deleteOneBookOutsideByBookId(int bookId);

    /**
     * 删除具体某一本书时，如果书未外借，最大数量减一，剩余数量减一
     * @param bookId bookId
     */
    void deleteOneBookInsideByBookId(int bookId);


}
