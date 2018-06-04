package services;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReturnBook {
    @POST("/api/borrower/borrowedBooks/{id}/return")
    Observable<Response<Void>> returnBorrowedBook(@Path("id") String id);
}
