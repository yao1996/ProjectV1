package info.ykqfrost.dao;

import info.ykqfrost.beans.LogBean;

import java.util.ArrayList;

/**
 * @author YaoKeQi
 * @date 2017/10/30
 */
public interface BorrowReturnDao {

    /**
     * 借书，book表中相应书籍remain-1
     * @param bookId 编号
     * @return 影响数据库行数
     */
    int borrowBook(int bookId);

    /**
     * 还书，book表中相应书籍remain+1
     * @param bookId 编号
     * @return 影响数据库行数
     */
    int returnBook(int bookId);


    /**
     * 设置log中归还时间
     * @param logBean 页面上传的内容
     * @return 影响数据库行数
     */
    int returnLog(LogBean logBean);

    /**
     * 添加一行log信息
     * @param logBean 页面上传的内容
     * @return 影响数据库行数
     */
    int borrowLog(LogBean logBean);

    /**
     * 返回未归还书的log
     * @return 返回未归还书的log
     */
    ArrayList<LogBean> selectUnReturned();

    /**
     * 返回所有log
     * @return 返回所有log
     */
    ArrayList<LogBean> selectAllLog();

    /**
     * 查找对同一本书，用户是否未还，若是，不允许借阅
     * @param logBean logBean
     * @return br_id 的列表
     */
    ArrayList<Integer> selectBorrowNum(LogBean logBean);

    /**
     * 查找书的剩余数量
     * @param bookId bookId
     * @return 剩余数量
     */
    int selectRemainNum(int bookId);
}
