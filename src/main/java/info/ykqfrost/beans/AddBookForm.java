package info.ykqfrost.beans;

/**
 * @author YaoKeQi
 * @date 2017/11/10
 */
public class AddBookForm {
    private long isbn;
    private int totalNum;
    private String location;
    private boolean outPermission;

    public boolean isOutPermission() {
        return outPermission;
    }

    public void setOutPermission(boolean outPermission) {
        this.outPermission = outPermission;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
