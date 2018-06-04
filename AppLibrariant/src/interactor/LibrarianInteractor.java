package interactor;

import io.reactivex.schedulers.Schedulers;
import models.BookInfo;
import services.*;

public class LibrarianInteractor {

    public void getBooks(OnGetBooksCompleteListener listener) {
        ApiClient.getInstance().create(GetBooks.class)
                .getBooks()
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribe(response -> {
                            switch (response.code()) {
                                case 200: {
                                    listener.onGetBooksComplete(response.body());
                                }
                                break;

                                default: {
                                    listener.onError(response.message());
                                    break;
                                }
                            }
                        },
                        error -> listener.onError(error.getMessage()));
    }

    public void getBorrowedBooks(OnGetBorrowedBooksCompleteListener listener) {
        ApiClient.getInstance().create(GetBorrowedBooks.class)
                .getBorrowedBooks()
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribe(response -> {
                            switch (response.code()) {
                                case 200: {
                                    listener.onGetBorrowedBooksComplete(response.body());
                                    break;
                                }

                                default: {
                                    listener.onError(response.message());
                                    break;
                                }
                            }
                        },
                        error -> listener.onError(error.getMessage()));
    }

    public void addBook(BookInfo bookInfo, OnRequestCompleteListener listener) {
        ApiClient.getInstance().create(AddBook.class)
                .addBook(bookInfo)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribe(response -> {
                            switch (response.code()) {
                                case 201: {
                                    listener.onRequestSuccess();
                                }
                                break;

                                default: {
                                    listener.onError(response.message());
                                    break;
                                }
                            }
                        },
                        error -> listener.onError(error.getMessage()));
    }

    public void updateBook(String bookID, BookInfo bookInfo, OnRequestCompleteListener listener) {
        ApiClient.getInstance().create(EditBook.class)
                .editBooks(bookID, bookInfo)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribe(response -> {
                            switch (response.code()) {
                                case 200: {
                                    listener.onRequestSuccess();
                                }
                                break;

                                default: {
                                    listener.onError(response.message());
                                    break;
                                }


                            }
                        },
                        error -> listener.onError(error.getMessage()));
    }

    public void deleteBook(String bookID, OnRequestCompleteListener listener) {
        ApiClient.getInstance().create(DeleteBook.class)
                .delBooks(bookID)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribe(response -> {
                            switch (response.code()) {
                                case 200: {
                                    listener.onRequestSuccess();
                                    break;
                                }
                                default: {
                                    listener.onError(response.message());
                                    break;
                                }
                            }
                        },
                        error -> listener.onError(error.getMessage()));
    }

    public void returnBorrowedBook(String borrowedBookID, OnRequestCompleteListener listener) {
        ApiClient.getInstance().create(ReturnBook.class)
                .returnBorrowedBook(borrowedBookID)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribe(response -> {
                            switch (response.code()) {
                                case 200: {
                                    listener.onRequestSuccess();
                                    break;
                                }
                                default: {
                                    listener.onError(response.message());
                                    break;
                                }
                            }
                        },
                        error -> listener.onError(error.getMessage()));
    }
}
