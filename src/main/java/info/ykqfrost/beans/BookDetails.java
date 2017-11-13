package info.ykqfrost.beans;

/**
 * @date  2017/10/15.
 * @author YaoKeQi
 */
public class BookDetails {
    private int typeId;
    private long isbn10;
    private long isbn13;
    private String bookName;
    private String author;
    private String coverImageUrl;
    private String introduce;
    private int totalNum;
    private int remainNum;
    private String location;
    private boolean outPermission;

    public boolean isOutPermission() {
        return outPermission;
    }

    public void setOutPermission(boolean outPermission) {
        this.outPermission = outPermission;
    }

    @Override
    public String toString() {
        return "BookDetails{" +
                "typeId=" + typeId +
                ", isbn10=" + isbn10 +
                ", isbn13=" + isbn13 +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", coverImageUrl='" + coverImageUrl + '\'' +
                ", introduce='" + introduce + '\'' +
                ", totalNum=" + totalNum +
                ", remainNum=" + remainNum +
                ", location='" + location + '\'' +
                '}';
    }

    public long getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(long isbn10) {
        this.isbn10 = isbn10;
    }

    public long getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(long isbn13) {
        this.isbn13 = isbn13;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(int remainNum) {
        this.remainNum = remainNum;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }


    public String getIntroduce() {
        return introduce;
    }
}
