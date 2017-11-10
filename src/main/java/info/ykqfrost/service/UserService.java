package info.ykqfrost.service;

import info.ykqfrost.beans.LoginForm;
import info.ykqfrost.beans.Reader;
import info.ykqfrost.dao.ManagerDao;
import info.ykqfrost.dao.ReaderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @author YaoKeQi
 * @date 2017/10/27
 */
@Service
public class UserService {
    private final String admin = "admin";
    private final String reader = "reader";
    private ReaderDao readerDao;
    private ManagerDao managerDao;
    @Autowired
    public void setReaderDao(ReaderDao readerDao) {
        this.readerDao = readerDao;
    }
    @Autowired
    public void setManagerDao(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean register(Reader reader) throws Exception {
        try {
            return readerDao.createReader(reader)==1;
        }catch (Exception e){
            throw new Exception();
        }
    }

    public ArrayList<Reader> selectAll() {
        return readerDao.selectAll();
    }

    public Reader selectOneById(int userId){
        return readerDao.selectOneById(userId);
    }

    public Reader login (LoginForm form) {
        if (reader.equals(form.getAuthority())) {
            return readerDao.login(form);
        }else if (admin.equals(form.getAuthority())) {
            return managerDao.managerLogin(form);
        }
        return null;
    }
}
