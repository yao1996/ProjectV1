package info.ykqfrost.dao;

import info.ykqfrost.beans.LogBean;
import info.ykqfrost.beans.Reader;
import java.util.ArrayList;
import java.util.Date;

public interface BorrowReturnDao {
    int borrowBook(int var1);

    int returnBook(int var1);

    int borrowBooks(int var1);

    int returnBooks(int var1);

    int returnLog(LogBean var1);

    int borrowLog(LogBean var1);

    ArrayList<LogBean> selectUnReturned();

    ArrayList<LogBean> selectAllLog();

    ArrayList<LogBean> selectAllLogOne(String var1);

    ArrayList<LogBean> selectUnReturnedOne(String var1);

    int selectBorrowNum(String var1);

    boolean outPermission(int var1);

    int selectRemainNum(int var1);

    int lessMoney(Reader var1);

    String selectBorrowStu(int var1);

    Date selectBorrowDate(int var1);

    int selectBorrowNumOneBook(String var1, int var2);

    Boolean isBorrowed(int var1);

    ArrayList<LogBean> searchLog(String var1);

    ArrayList<LogBean> searchUnReturnedLog(String var1);
}
