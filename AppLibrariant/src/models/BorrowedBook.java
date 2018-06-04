package models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BorrowedBook {
    private String id;
    private String title;
    private String author;
    private int quantity;
    private long createdDate;
    private long returnedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public long getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(long returnedDate) {
        this.returnedDate = returnedDate;
    }

    public String[] toArray() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyy HH:mm");
        String[] result = new String[5];
        result[0] = title;
        result[1] = author;
        result[2] = quantity + "";
        result[3] = simpleDateFormat.format(new Date(createdDate));
        String returnDate = returnedDate == -1? "not returned yet" : simpleDateFormat.format(new Date(returnedDate));
        result[4] = returnDate;
        return result;
    }
}
