package info.ykqfrost.beans;

public class Book {
    private int typeId;
    private long bookId;
    private boolean isBorrowed;

    public Book() {
    }

    public int getTypeId() {
        return this.typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public long getBookId() {
        return this.bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public boolean isBorrowed() {
        return this.isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.isBorrowed = borrowed;
    }
}
