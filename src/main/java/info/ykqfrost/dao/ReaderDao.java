package info.ykqfrost.dao;

import info.ykqfrost.beans.LoginForm;
import info.ykqfrost.beans.Reader;
import org.apache.ibatis.exceptions.PersistenceException;

import java.util.ArrayList;

/**
 * @author YaoKeQi
 * @date 2017/10/26
 */
public interface ReaderDao {
    /**
     * 用于管理员管理读者帐号时得到列表
     * @return list of user containing userId and username
     */
    ArrayList<Reader> selectAll();

    /**
     * 用于读者查看自己账户
     * @param userId userID
     * @return Reader
     */
    Reader selectOneById(int userId);

    /**
     * 读者登录
     * @param form loginForm
     * @return 返回学号和押金余额
     */
    Reader login (LoginForm form);

    /**
     * 注册
     * @param reader as createReader form
     * @return 数据库受影响的行数 若插入成功 返回1
     * @throws PersistenceException 用户名已存在时 抛出异常
     */
    int createReader(Reader reader) throws PersistenceException;
}
