package info.ykqfrost.dao;

import info.ykqfrost.beans.BookDetails;
import info.ykqfrost.beans.LoginForm;
import info.ykqfrost.beans.Reader;
import java.util.ArrayList;

public interface ManagerDao {
    Reader managerLogin(LoginForm var1);

    int updateBookDetail(BookDetails var1);

    ArrayList<BookDetails> selectAllBooks();
}
