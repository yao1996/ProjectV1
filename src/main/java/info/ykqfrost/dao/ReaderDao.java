package info.ykqfrost.dao;

import info.ykqfrost.beans.LoginForm;
import info.ykqfrost.beans.Reader;
import java.util.ArrayList;
import org.apache.ibatis.exceptions.PersistenceException;

public interface ReaderDao {
    ArrayList<Reader> selectAll();

    Reader selectOneById(String var1);

    Reader login(LoginForm var1);

    int createReader(Reader var1) throws PersistenceException;

    int recharge(Reader var1);
}
