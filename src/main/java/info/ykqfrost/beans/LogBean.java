package info.ykqfrost.beans;

import java.util.Date;

public class LogBean {
    private int borrowId;
    private int bookId;
    private String readerUsername;
    private Date borrowDate;
    private Date returnDate;
    private String bookName;
    private double fee;
    private String type;

    public LogBean() {
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBorrowDateWell() {
        String s = this.borrowDate.toLocaleString();
        String[] strings = s.split(" ");
        return strings[0];
    }

    public String getReturnDateWell() {
        if (this.returnDate == null) {
            return "";
        } else {
            String s = this.returnDate.toLocaleString();
            String[] strings = s.split(" ");
            return strings[0];
        }
    }

    public double getFee() {
        return this.fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getBookName() {
        return this.bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBorrowId() {
        return this.borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public int getBookId() {
        return this.bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getReaderUsername() {
        return this.readerUsername;
    }

    public void setReaderUsername(String readerUsername) {
        this.readerUsername = readerUsername;
    }

    public Date getBorrowDate() {
        return this.borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return this.returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
