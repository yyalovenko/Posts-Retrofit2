package app.controller;

import app.entity.Post;
import app.entity.PostResponse;
import app.model.PostModel;
import app.utils.AppStarter;
import app.utils.Constants;
import app.view.PostByIdView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import retrofit2.Response;

import java.util.Optional;

public class PostByIdController {

    PostModel model;
    PostByIdView view;

    public PostByIdController(PostModel model, PostByIdView view) {
        this.model = model;
        this.view = view;
    }

    public void getPostById() {
        view.getOutput(readPostById(
                Integer.parseInt(view.getData())
        ));
        AppStarter.startApp();
    }

    private String readPostById(int id) {
        Optional<Response<PostResponse>> optional = model.fetchPostById(id);

        if (optional.isEmpty()) {
            return Constants.NO_DATA_MSG;
        } else {
            Gson gson = new Gson();
            Post post = gson.fromJson(String.valueOf(optional.get().body()),
                    new TypeToken<Post>() {}.getType());
            return "User: id " + post.getUserId() + ", " + post.getId() +
                    " " + post.getTitle() + ", " + post.getBody();
        }
    }
}
