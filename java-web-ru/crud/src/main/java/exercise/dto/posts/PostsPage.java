package exercise.dto.posts;

import java.util.List;
import exercise.model.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
// BEGIN
public class PostsPage {
    private List<Post> posts;
    private int currentPage;
}
// END


