package services;

import io.reactivex.Observable;
import models.BookInfo;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EditBook {
    @PUT("/api/librarian/books/{id}")
    Observable<Response<Void>> editBooks(@Path("id") String id, @Body BookInfo bookinfo);
}
