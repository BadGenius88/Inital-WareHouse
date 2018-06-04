package models;

public class BookInfo {
    private String title;
    private String author;
    private String description;
    private int quantity;

    public BookInfo(String title, String author, String description, int quantity) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.quantity = quantity;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String[] toArray() {
        String[] bookInfo = new String[4];
        bookInfo[0] = title;
        bookInfo[1] = author;
        bookInfo[2] = description;
        bookInfo[3] = quantity + "";
        return bookInfo;
    }
}
