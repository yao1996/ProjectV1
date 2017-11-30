package info.ykqfrost.beans;

public class AddBookForm {
    private long isbn;
    private int totalNum;
    private String location;
    private boolean outPermission;
    private double price;

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

    public long getIsbn() {
        return this.isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public int getTotalNum() {
        return this.totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
