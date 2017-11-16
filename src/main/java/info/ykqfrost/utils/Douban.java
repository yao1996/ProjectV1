package info.ykqfrost.utils;

import com.dongxuexidu.douban4j.model.app.DoubanException;
import com.dongxuexidu.douban4j.model.common.DoubanAttributeObj;
import com.dongxuexidu.douban4j.model.subject.DoubanSubjectObj;
import com.dongxuexidu.douban4j.service.DoubanBookMovieMusicService;
import info.ykqfrost.beans.AddBookForm;
import info.ykqfrost.beans.BookDetails;

import java.io.IOException;
import java.util.List;

/**
 * @author YaoKeQi
 * @date 2017/11/10
 * 工具类，用于将AddBookForm转化为bookDetail，从豆瓣获取具体信息
 */
public class Douban {

    public static BookDetails getBookDetail(AddBookForm addBookForm) throws IOException, DoubanException {
        BookDetails bookDetails = new BookDetails();
        bookDetails.setTotalNum(addBookForm.getTotalNum());
        bookDetails.setLocation(addBookForm.getLocation());
        bookDetails.setOutPermission(addBookForm.isOutPermission());

        DoubanBookMovieMusicService service = new DoubanBookMovieMusicService();
        DoubanSubjectObj book = service.getBookInfoByISBN(String.valueOf(addBookForm.getIsbn()));
        List<DoubanAttributeObj> attributeObjs = book.getAttributes();
        bookDetails.setIntroduce(book.getSummary());
        for (DoubanAttributeObj attribute : attributeObjs) {
            switch (attribute.getName()){
                case "isbn10" :
                    bookDetails.setIsbn10(Long.parseLong(attribute.getValue()));
                    break;
                case "isbn13" :
                    bookDetails.setIsbn13(Long.parseLong(attribute.getValue()));
                    break;
                case "title" :
                    bookDetails.setBookName(attribute.getValue());
                    break;
                case "author" :
                    bookDetails.setAuthor(attribute.getValue());
                    break;
                default:
                    break;
            }
        }
        bookDetails.setCoverImageUrl(book.getLinkByRel("image"));
        return bookDetails;
    }
}
