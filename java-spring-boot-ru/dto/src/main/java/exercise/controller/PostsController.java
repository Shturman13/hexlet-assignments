package exercise.controller;

import exercise.dto.CommentDTO;
import exercise.dto.PostDTO;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    // GET /posts — Список всех постов
    @GetMapping
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream()
                .map(this::convertToPostDTO)
                .toList();
    }

    // GET /posts/{id} — Просмотр конкретного поста
    @GetMapping("/{id}")
    public PostDTO getPostById(@PathVariable Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post with id " + id + " not found"));
        return convertToPostDTO(post);
    }

    // Метод для конвертации Post в PostDTO
    private PostDTO convertToPostDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setBody(post.getBody());
        List<CommentDTO> commentDTOs = commentRepository.findByPostId(post.getId()).stream()
                .map(comment -> {
                    CommentDTO commentDTO = new CommentDTO();
                    commentDTO.setId(comment.getId());
                    commentDTO.setBody(comment.getBody());
                    return commentDTO;
                })
                .toList();
        postDTO.setComments(commentDTOs);
        return postDTO;
    }
}