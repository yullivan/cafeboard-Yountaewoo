package cafeboard.Comment.DTO;

import java.time.LocalDateTime;

public record CreateCommentResponse(
        String content,
        Long postId,
        LocalDateTime createdTime
) {
}
