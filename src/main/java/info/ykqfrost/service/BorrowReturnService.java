package info.ykqfrost.service;

import info.ykqfrost.beans.LogBean;
import info.ykqfrost.dao.BookDao;
import info.ykqfrost.dao.BorrowReturnDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @author YaoKeQi
 * @date 2017/10/30
 */
@Service
public class BorrowReturnService {
    private final BorrowReturnDao borrowReturnDao;
    private final static int MAXOUTBORROWNUM = 2;

    @Autowired
    public BorrowReturnService(BorrowReturnDao borrowReturnDao) {
        this.borrowReturnDao = borrowReturnDao;
    }

    @Transactional(rollbackFor = Exception.class)
    public void borrowBook(LogBean logBean) throws Exception {
        boolean outPermission = borrowReturnDao.outPermission(logBean.getBookId());
        if (! outPermission) {
            throw new Exception("this book is not allow to be borrowed out !");
        }
        int remain = borrowReturnDao.selectRemainNum(logBean.getBookId());
        if (remain==0) {
            throw new Exception("there is no more this book !");
        }
        int num = borrowReturnDao.selectBorrowNum(logBean.getReaderUsername());
        if (num >= MAXOUTBORROWNUM) {
            throw new Exception("the reader has borrowed 2 books !");
        }
        boolean b1 = borrowReturnDao.borrowBook(logBean.getBookId()) == 1;
        boolean b2 = borrowReturnDao.borrowLog(logBean) == 1;
        if (!b1 || !b2){
            throw new Exception("some error happened in database,please call the producer !");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void returnBook(LogBean logBean) throws Exception {
        boolean b1 = borrowReturnDao.returnBook(logBean.getBookId()) == 1;
        boolean b2 = borrowReturnDao.returnLog(logBean) == 1;
        if (!b1 || !b2){
            throw new Exception("return book failed ！");
        }
    }
}