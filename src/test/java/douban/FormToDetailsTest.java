package douban;

import com.dongxuexidu.douban4j.model.app.DoubanException;
import info.ykqfrost.beans.AddBookForm;
import info.ykqfrost.beans.BookDetails;
import info.ykqfrost.utils.Douban;
import org.junit.Test;

import java.io.IOException;

/**
 * @author YaoKeQi
 * @date 2017/11/10
 */
public class FormToDetailsTest {
    @Test
    public void formToDetails() {
        AddBookForm addBookForm = new AddBookForm();
        addBookForm.setIsbn(7505715666L);
        addBookForm.setLocation("A-1");
        addBookForm.setTotalNum(5);
        try {
            BookDetails bookDetails = Douban.getBookDetail(addBookForm);
            System.out.println(bookDetails);
        } catch (IOException | DoubanException e) {
            System.out.println("IO exception");
        }
    }
}
