package cafeboard.Comment.DTO;

public record CreateComment(
        String content,
        Long postId
) {
}
