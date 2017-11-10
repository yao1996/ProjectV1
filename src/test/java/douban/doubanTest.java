package douban;

import com.dongxuexidu.douban4j.model.app.DoubanException;
import com.dongxuexidu.douban4j.model.common.DoubanAttributeObj;
import com.dongxuexidu.douban4j.model.common.DoubanLinkObj;
import com.dongxuexidu.douban4j.model.subject.DoubanSubjectObj;
import com.dongxuexidu.douban4j.service.DoubanBookMovieMusicService;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author YaoKeQi
 * @date 2017/11/5
 */
public class doubanTest {
    @Test
    public void bookIsbnTest() throws IOException, DoubanException {
        DoubanBookMovieMusicService service = new DoubanBookMovieMusicService();
        DoubanSubjectObj book = service.getBookInfoByISBN("7505715666");
        List<DoubanAttributeObj> list = book.getAttributes();
        for(DoubanAttributeObj attribute : list) {
            System.out.println(attribute.getName() + "  " + attribute.getValue());
        }
        List<DoubanLinkObj> linkObjs = book.getLinks();
        for (DoubanLinkObj obj : linkObjs) {
            System.out.println(obj.getObjName() + " " + obj.getRel() +" "+obj.getHref());
        }

    }
}
