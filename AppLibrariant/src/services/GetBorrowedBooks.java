package services;

import io.reactivex.Observable;
import models.BorrowedBook;
import retrofit2.Response;
import retrofit2.http.GET;

import java.util.List;

public interface GetBorrowedBooks {
    @GET("/api/borrower/borrowedBooks")
    Observable<Response<List<BorrowedBook>>> getBorrowedBooks();
}
