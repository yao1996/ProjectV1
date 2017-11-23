package info.ykqfrost.beans;

public class DeleteForm {
    private int deleteId;
    private int bookId;
    private long isbn13;
    private String reason;
    private String method;
    private String bookName;

    public DeleteForm() {
    }

    public String getBookName() {
        return this.bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getDeleteId() {
        return this.deleteId;
    }

    public void setDeleteId(int deleteId) {
        this.deleteId = deleteId;
    }

    public int getBookId() {
        return this.bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public long getIsbn13() {
        return this.isbn13;
    }

    public void setIsbn13(long isbn13) {
        this.isbn13 = isbn13;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
