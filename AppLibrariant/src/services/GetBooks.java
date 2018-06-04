package services;

import io.reactivex.Observable;
import models.BookPreview;
import retrofit2.Response;
import retrofit2.http.GET;

import java.util.List;

public interface GetBooks {
    @GET("/api/books")
    Observable<Response<List<BookPreview>>> getBooks();
}
