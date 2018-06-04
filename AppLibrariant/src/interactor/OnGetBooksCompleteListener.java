package interactor;

import models.BookPreview;

import java.util.List;

public interface OnGetBooksCompleteListener {
    void onGetBooksComplete(List<BookPreview> books);
    void onError(String message);
}
