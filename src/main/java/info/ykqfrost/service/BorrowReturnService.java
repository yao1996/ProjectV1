package info.ykqfrost.service;

import info.ykqfrost.beans.DeleteForm;
import info.ykqfrost.beans.LogBean;
import info.ykqfrost.beans.Reader;
import info.ykqfrost.dao.BookDao;
import info.ykqfrost.dao.BorrowReturnDao;
import info.ykqfrost.utils.MoneyCalcu;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BorrowReturnService {
    private final BorrowReturnDao borrowReturnDao;
    private BookDao bookDao;
    private BookService bookService;
    private static final int MAXOUTBORROWNUM = 2;
    private UserService userService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Autowired
    public BorrowReturnService(BorrowReturnDao borrowReturnDao) {
        this.borrowReturnDao = borrowReturnDao;
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public void borrowBook(LogBean logBean) throws Exception {
        Reader reader = this.userService.selectOneById(logBean.getReaderUsername());
        if (reader == null) {
            throw new Exception("the reader has not register yet !");
        } else if (this.borrowReturnDao.isBorrowed(logBean.getBookId()) != null && !this.borrowReturnDao.isBorrowed(logBean.getBookId()).booleanValue()) {
            if (this.borrowReturnDao.selectBorrowNumOneBook(logBean.getReaderUsername(), logBean.getBookId()) == 1) {
                throw new Exception("this reader has borrowed one book of this type !");
            } else {
                boolean outPermission = this.borrowReturnDao.outPermission(logBean.getBookId());
                if (!outPermission) {
                    throw new Exception("this book is not allow to be borrowed out !");
                } else {
                    int remain = this.borrowReturnDao.selectRemainNum(logBean.getBookId());
                    if (remain == 0) {
                        throw new Exception("there is no more this book !");
                    } else {
                        int num = this.borrowReturnDao.selectBorrowNum(logBean.getReaderUsername());
                        if (num >= 2) {
                            throw new Exception("the reader has borrowed 2 books !");
                        } else {
                            boolean b1 = this.borrowReturnDao.borrowBook(logBean.getBookId()) == 1;
                            boolean b2 = this.borrowReturnDao.borrowLog(logBean) == 1;
                            boolean b3 = this.borrowReturnDao.borrowBooks(logBean.getBookId()) == 1;
                            if (!b1 || !b2 || !b3) {
                                throw new Exception("some error happened in database,please call the producer !");
                            }
                        }
                    }
                }
            }
        } else {
            throw new Exception("this book has been borrowed !");
        }
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public double returnBook(LogBean logBean) throws Exception {
        if (this.bookService.selectPriceByBookId(logBean.getBookId()) == 0.0D) {
            throw new Exception("there is no this book !");
        } else if (this.borrowReturnDao.isBorrowed(logBean.getBookId()) != null && this.borrowReturnDao.isBorrowed(logBean.getBookId()).booleanValue()) {
            String username = this.borrowReturnDao.selectBorrowStu(logBean.getBookId());
            Date borrowDate = this.borrowReturnDao.selectBorrowDate(logBean.getBookId());
            int lessMoney = MoneyCalcu.getMoney(borrowDate, new Date());
            Reader reader = new Reader();
            Reader readerExisted = this.userService.selectOneById(username);
            reader.setUsername(username);
            reader.setAccount((double)lessMoney);
            if (readerExisted.getAccount() < reader.getAccount()) {
                throw new Exception("Money is not enough,please recharge first !");
            } else {
                this.borrowReturnDao.lessMoney(reader);
                logBean.setFee((double)lessMoney);
                boolean b1 = this.borrowReturnDao.returnBook(logBean.getBookId()) == 1;
                boolean b2 = this.borrowReturnDao.returnLog(logBean) == 1;
                boolean b3 = this.borrowReturnDao.returnBooks(logBean.getBookId()) == 1;
                if (b1 && b2 && b3) {
                    return (double)lessMoney;
                } else {
                    throw new Exception("return book failed ！");
                }
            }
        } else {
            throw new Exception("this book has not been borrowed !");
        }
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public double damageBook(LogBean logBean) throws Exception {
        if (this.bookService.selectPriceByBookId(logBean.getBookId()) == 0.0D) {
            throw new Exception("there is no this book !");
        } else if (this.borrowReturnDao.isBorrowed(logBean.getBookId()) != null && this.borrowReturnDao.isBorrowed(logBean.getBookId()).booleanValue()) {
            String username = this.borrowReturnDao.selectBorrowStu(logBean.getBookId());
            Date borrowDate = this.borrowReturnDao.selectBorrowDate(logBean.getBookId());
            double lessMoney = (double)MoneyCalcu.getMoney(borrowDate, new Date()) + this.bookService.selectPriceByBookId(logBean.getBookId());
            Reader reader = new Reader();
            Reader readerExisted = this.userService.selectOneById(username);
            reader.setUsername(username);
            reader.setAccount(lessMoney);
            if (readerExisted.getAccount() < reader.getAccount()) {
                throw new Exception("Money is not enough,please recharge first !");
            } else {
                this.borrowReturnDao.lessMoney(reader);
                logBean.setFee(lessMoney);
                boolean b1 = this.borrowReturnDao.returnBook(logBean.getBookId()) == 1;
                boolean b2 = this.borrowReturnDao.returnLog(logBean) == 1;
                boolean b3 = this.borrowReturnDao.returnBooks(logBean.getBookId()) == 1;
                DeleteForm deleteForm = new DeleteForm();
                deleteForm.setMethod("byBookId");
                deleteForm.setBookId(logBean.getBookId());
                deleteForm.setReason("book is damaged");
                this.bookService.deleteBook(deleteForm);
                if (b1 && b2 && b3) {
                    return lessMoney;
                } else {
                    throw new Exception("return book failed ！");
                }
            }
        } else {
            throw new Exception("this book has not been borrowed !");
        }
    }

    public ArrayList<LogBean> selectAllLog() {
        ArrayList<LogBean> logBeans = this.borrowReturnDao.selectAllLog();
        Iterator var2 = logBeans.iterator();

        while(var2.hasNext()) {
            LogBean logBean = (LogBean)var2.next();
            logBean.setBookName(this.bookDao.selectBookNameByBookId(logBean.getBookId()));
        }

        return logBeans;
    }

    public ArrayList<LogBean> selectUnReturned() {
        ArrayList<LogBean> logBeans = this.borrowReturnDao.selectUnReturned();
        Iterator var2 = logBeans.iterator();

        while(var2.hasNext()) {
            LogBean logBean = (LogBean)var2.next();
            logBean.setBookName(this.bookDao.selectBookNameByBookId(logBean.getBookId()));
        }

        return logBeans;
    }

    public ArrayList<LogBean> selectAllLogOne(String username) {
        ArrayList<LogBean> logBeans = this.borrowReturnDao.selectAllLogOne(username);
        Iterator var3 = logBeans.iterator();

        while(var3.hasNext()) {
            LogBean logBean = (LogBean)var3.next();
            logBean.setBookName(this.bookDao.selectBookNameByBookId(logBean.getBookId()));
        }

        return logBeans;
    }

    public ArrayList<LogBean> selectUnReturnedOne(String username) {
        ArrayList<LogBean> logBeans = this.borrowReturnDao.selectUnReturnedOne(username);
        Iterator var3 = logBeans.iterator();

        while(var3.hasNext()) {
            LogBean logBean = (LogBean)var3.next();
            logBean.setBookName(this.bookDao.selectBookNameByBookId(logBean.getBookId()));
        }

        return logBeans;
    }

    public ArrayList<LogBean> searchLog(String search) {
        ArrayList<LogBean> logBeans = this.borrowReturnDao.searchLog(search);
        Iterator var3 = logBeans.iterator();

        while(var3.hasNext()) {
            LogBean logBean = (LogBean)var3.next();
            logBean.setBookName(this.bookDao.selectBookNameByBookId(logBean.getBookId()));
        }

        return logBeans;
    }

    public ArrayList<LogBean> searchUnReturnedLog(String search) {
        ArrayList<LogBean> logBeans = this.borrowReturnDao.searchUnReturnedLog(search);
        Iterator var3 = logBeans.iterator();

        while(var3.hasNext()) {
            LogBean logBean = (LogBean)var3.next();
            logBean.setBookName(this.bookDao.selectBookNameByBookId(logBean.getBookId()));
        }

        return logBeans;
    }
}
