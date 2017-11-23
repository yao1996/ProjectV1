package info.ykqfrost.beans;

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
    private double price;
    private String publisher;

    public BookDetails() {
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isOutPermission() {
        return this.outPermission;
    }

    public void setOutPermission(boolean outPermission) {
        this.outPermission = outPermission;
    }

    public long getIsbn10() {
        return this.isbn10;
    }

    public void setIsbn10(long isbn10) {
        this.isbn10 = isbn10;
    }

    public long getIsbn13() {
        return this.isbn13;
    }

    public void setIsbn13(long isbn13) {
        this.isbn13 = isbn13;
    }

    public String getCoverImageUrl() {
        return this.coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public int getTotalNum() {
        return this.totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getRemainNum() {
        return this.remainNum;
    }

    public void setRemainNum(int remainNum) {
        this.remainNum = remainNum;
    }

    public String getLocation() {
        return this.location;
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
        return this.typeId;
    }

    public String getBookName() {
        return this.bookName;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getIntroduce() {
        return this.introduce;
    }
}
