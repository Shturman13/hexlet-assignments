package exercise.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Post {
    private Long userId;
    private String slug;
    private String title;
    private String body;
}
