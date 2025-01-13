package cafeboard.Comment.DTO;


public record CreateCommentResponse(
        String content,
        Long postId
) {
}
