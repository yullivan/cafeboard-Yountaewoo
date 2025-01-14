package cafeboard.Comment.DTO;

import java.util.List;

public record FindAllComment(
        List<Comment> comments
) {
    public record Comment(
            String content,
            Long id
    ) {
    }
}
