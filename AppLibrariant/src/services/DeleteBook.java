package services;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public interface DeleteBook {
    @DELETE("/api/librarian/books/{id}")
    Observable<Response<Void>> delBooks(@Path("id") String id);
}
