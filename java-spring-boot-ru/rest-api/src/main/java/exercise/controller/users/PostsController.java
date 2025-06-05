package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

@RestController
@RequestMapping("/api/users")
public class PostsController {

    // Получение списка постов пользователя по его id
    @GetMapping("/{id}/posts")
    public List<Post> getUserPosts(@PathVariable Long id) {
        return Data.getPosts().stream()
                .filter(post -> post.getUserId().equals(id))
                .toList();
    }

    // Создание нового поста для пользователя
    @PostMapping("/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@PathVariable Long id, @RequestBody Post post) {
        post.setUserId(id);
        Data.getPosts().add(post);
        return post;
    }
}
