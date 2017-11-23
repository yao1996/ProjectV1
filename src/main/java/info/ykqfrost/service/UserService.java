package info.ykqfrost.service;

import info.ykqfrost.beans.LoginForm;
import info.ykqfrost.beans.Reader;
import info.ykqfrost.dao.ManagerDao;
import info.ykqfrost.dao.ReaderDao;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private ReaderDao readerDao;
    private ManagerDao managerDao;

    public UserService() {
    }

    @Autowired
    public void setReaderDao(ReaderDao readerDao) {
        this.readerDao = readerDao;
    }

    @Autowired
    public void setManagerDao(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public boolean register(Reader reader) throws Exception {
        try {
            return this.readerDao.createReader(reader) == 1;
        } catch (Exception var3) {
            throw new Exception();
        }
    }

    public ArrayList<Reader> selectAll() {
        return this.readerDao.selectAll();
    }

    public Reader selectOneById(String username) {
        return this.readerDao.selectOneById(username);
    }

    public Reader login(LoginForm form) {
        String admin = "admin";
        String reader = "reader";
        if (reader.equals(form.getAuthority())) {
            return this.readerDao.login(form);
        } else {
            return admin.equals(form.getAuthority()) ? this.managerDao.managerLogin(form) : null;
        }
    }

    public void recharge(Reader reader) throws Exception {
        if (this.readerDao.recharge(reader) == 0) {
            throw new Exception("recharge failed !");
        }
    }
}
