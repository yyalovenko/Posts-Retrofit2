package app.controller;

import app.entity.Post;
import app.entity.PostsResponse;
import app.model.PostModel;
import app.utils.AppStarter;
import app.utils.Constants;
import app.view.PostsView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import retrofit2.Response;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class PostsController {

    PostModel model;
    PostsView view;

    public PostsController(PostModel model, PostsView view) {
        this.model = model;
        this.view = view;
    }

    public void getPosts() {
        view.getOutput(readPosts());
        AppStarter.startApp();
    }

    private String readPosts() {
        Optional<Response<PostsResponse>> optional = model.fetchPosts();
        if (optional.isEmpty()) {
            return Constants.NO_DATA_MSG;
        } else {

            Gson gson = new Gson();
            List<Post> posts = gson.fromJson(String.valueOf(optional.get().body()),
                    new TypeToken<List<Post>>() {}.getType());

            StringBuilder stringBuilder = new StringBuilder();
            AtomicInteger cnt = new AtomicInteger(0);
            String str;

            for (Post post : posts) {
                str = cnt.incrementAndGet() + ") User: id " + post.getUserId() + ", " +
                        post.getId() + " " + post.getTitle() + ", " +
                        post.getBody() + "\n";
                stringBuilder.append(str);
            }
            return stringBuilder.toString();
        }
    }

}
