package app.network;

import app.entity.PostResponse;
import app.entity.PostsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("posts")
    Call<PostsResponse> getPosts();

    @GET("posts/{id}")
    Call<PostResponse> getPostById(@Path("id") int id);
}
