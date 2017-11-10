package mybatisTest;

import info.ykqfrost.beans.BookDetails;
import info.ykqfrost.dao.ManagerDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author YaoKeQi
 * @date 2017/10/30
 */
@SpringBootTest(classes = info.ykqfrost.SpringBoot.class)
@RunWith(SpringRunner.class)
public class UpdateTest {

    private ManagerDao managerDao;
    @Autowired
    public void setManagerDao(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }
    @Test
    public void updateTest() {
        BookDetails bookDetails = new BookDetails();
        bookDetails.setTypeId(1);
        bookDetails.setTotalNum(6);
        bookDetails.setRemainNum(6);
        int i = managerDao.updateBook(bookDetails);
        System.out.println(i);
    }
}
