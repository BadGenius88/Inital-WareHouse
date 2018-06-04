package models;

public class BookPreview {
    private String id;
    private String title;
    private String author;
    private int quantity;
    private int borrowedQuantity;

    public BookPreview(String mtitle, String mauthor, int mquantity, int mborrowedQuantity) {
         title = mtitle;
         author = mauthor;
         quantity = mquantity;
         borrowedQuantity = mborrowedQuantity;
    }


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

    public int getBorrowedQuantity() {
        return borrowedQuantity;
    }

    public void setBorrowedQuantity(int borrowedQuantity) {
        this.borrowedQuantity = borrowedQuantity;
    }
    public String[] toArray() {
        String[] arr = new String[4];
        arr[0] = title;
        arr[1] = author;
        arr[2] = quantity+"";
        arr[3] = borrowedQuantity+"";
        return arr;
    }
}
