package cafeboard.Post.DTO;

import java.util.List;

public record FindDetailPostResponse(
        List<Comment> comments,
        String content
) {
    public record Comment(
            String content
    ) {
    }
}
