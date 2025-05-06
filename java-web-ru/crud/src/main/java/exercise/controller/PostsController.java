package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.util.List;
import java.util.Map;

public class PostsController {
    private final PostRepository postRepository;
    // BEGIN
    public PostsController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = postRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Page not found"));
        var page = new PostPage(post);
        ctx.render("posts/show.jte", model("page", page));
    }

    public static void index(Context ctx) {

        int page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        int pageSize = 5;

        List<Post> posts = PostRepository.findAll(page, pageSize);
        PostsPage postsPage = new PostsPage(posts, page);
        ctx.render("posts/index.jte", Map.of("page", postsPage));
    }

    // END
}
