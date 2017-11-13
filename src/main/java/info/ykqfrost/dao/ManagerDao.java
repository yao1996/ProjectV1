package info.ykqfrost.dao;

import info.ykqfrost.beans.BookDetails;
import info.ykqfrost.beans.LoginForm;
import info.ykqfrost.beans.Reader;

import java.util.ArrayList;

/**
 * @author YaoKeQi
 * @date 2017/10/30
 */
public interface ManagerDao {
    /**
     * 管理员登录
     * @param form loginForm
     * @return username of manager
     */
    Reader managerLogin (LoginForm form);

    /**
     * 更新书籍信息,从豆瓣下载，不更新location和数量
     * @param bookDetails bookForm
     * @return 影响行数
     */
    int updateBookDetail(BookDetails bookDetails);


    /**
     * 取出所有书
     * @return list of all the books
     */
    ArrayList<BookDetails> selectAllBooks();
}
