package services;

import io.reactivex.Observable;
import models.BookInfo;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AddBook {
    @POST("/api/librarian/books")
    Observable<Response<Void>> addBook(@Body BookInfo bookInfo);
}
