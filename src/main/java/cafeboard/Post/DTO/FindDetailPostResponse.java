package cafeboard.Post.DTO;

import java.util.List;

public record FindDetailPostResponse(
        List<CommentResponse> comments,
        String content
) {
    public record CommentResponse(
            String content
    ) {
    }
}
