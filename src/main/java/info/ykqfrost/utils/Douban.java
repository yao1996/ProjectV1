package info.ykqfrost.utils;

import com.dongxuexidu.douban4j.model.app.DoubanException;
import com.dongxuexidu.douban4j.model.common.DoubanAttributeObj;
import com.dongxuexidu.douban4j.model.subject.DoubanSubjectObj;
import com.dongxuexidu.douban4j.service.DoubanBookMovieMusicService;
import info.ykqfrost.beans.AddBookForm;
import info.ykqfrost.beans.BookDetails;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Douban {
    public Douban() {
    }

    public static BookDetails getBookDetail(AddBookForm addBookForm) throws IOException, DoubanException {
        BookDetails bookDetails = new BookDetails();
        bookDetails.setTotalNum(addBookForm.getTotalNum());
        bookDetails.setLocation(addBookForm.getLocation());
        bookDetails.setOutPermission(addBookForm.isOutPermission());
        bookDetails.setPrice(addBookForm.getPrice());
        DoubanBookMovieMusicService service = new DoubanBookMovieMusicService();
        DoubanSubjectObj book = service.getBookInfoByISBN(String.valueOf(addBookForm.getIsbn()));
        List<DoubanAttributeObj> attributeObjs = book.getAttributes();
        bookDetails.setIntroduce(book.getSummary());
        Iterator var5 = attributeObjs.iterator();

        while(var5.hasNext()) {
            DoubanAttributeObj attribute = (DoubanAttributeObj)var5.next();
            String var7 = attribute.getName();
            byte var8 = -1;
            switch(var7.hashCode()) {
                case -1406328437:
                    if (var7.equals("author")) {
                        var8 = 3;
                    }
                    break;
                case -1179674731:
                    if (var7.equals("isbn10")) {
                        var8 = 0;
                    }
                    break;
                case -1179674728:
                    if (var7.equals("isbn13")) {
                        var8 = 1;
                    }
                    break;
                case 110371416:
                    if (var7.equals("title")) {
                        var8 = 2;
                    }
                    break;
                case 1447404028:
                    if (var7.equals("publisher")) {
                        var8 = 4;
                    }
            }

            switch(var8) {
                case 0:
                    bookDetails.setIsbn10(Long.parseLong(attribute.getValue()));
                    break;
                case 1:
                    bookDetails.setIsbn13(Long.parseLong(attribute.getValue()));
                    break;
                case 2:
                    bookDetails.setBookName(attribute.getValue());
                    break;
                case 3:
                    bookDetails.setAuthor(attribute.getValue());
                    break;
                case 4:
                    bookDetails.setPublisher(attribute.getValue());
            }
        }

        bookDetails.setCoverImageUrl(book.getLinkByRel("image"));
        return bookDetails;
    }
}
