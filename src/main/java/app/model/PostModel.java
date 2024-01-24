package app.model;

import app.entity.PostResponse;
import app.entity.PostsResponse;
import app.network.ApiClient;
import app.network.ApiService;
import retrofit2.Call;
import retrofit2.Response;

import java.util.Optional;

public class PostModel {

    public Optional<Response<PostsResponse>> fetchPosts() {

        ApiClient client = new ApiClient();
        ApiService service = client.getApiService();
        Call<PostsResponse> call = service.getPosts();
        Optional<Response<PostsResponse>> optional;

        try {
            optional = Optional.of(call.execute());
        } catch (Exception ex) {
            optional = Optional.empty();
        }

        return optional;
    }

    public Optional<Response<PostResponse>> fetchPostById(int id) {
        ApiClient client = new ApiClient();
        ApiService service = client.getApiService();
        Call<PostResponse> call = service.getPostById(id);
        Optional<Response<PostResponse>> optional;

        try {
            optional = Optional.of(call.execute());
        } catch (Exception ex) {
            optional = Optional.empty();
        }

        return optional;
    }

}
