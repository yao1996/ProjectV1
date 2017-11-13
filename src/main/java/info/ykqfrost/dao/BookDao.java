package info.ykqfrost.dao;

import info.ykqfrost.beans.BookDetails;

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
     * @param isbn 图书编号
     * @return 书籍类型编号
     */
    int selectByIsbn(long isbn);


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
     * 添加新书，自动生成每本书id
     * @param typeId 书籍类型编号
     * @return 更新的行数
     */
    int addSpecificBook(int typeId);

    /**
     * 通过书的id删除书籍
     * @param bookID 图书id
     * @return 更新的行数
     */
    int deleteBookByTypeId(int bookID);
}
