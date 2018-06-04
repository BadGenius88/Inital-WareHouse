package interactor;

import models.BorrowedBook;

import java.util.List;

public interface OnGetBorrowedBooksCompleteListener {
    void onGetBorrowedBooksComplete(List<BorrowedBook> books);
    void onError(String message);
}
