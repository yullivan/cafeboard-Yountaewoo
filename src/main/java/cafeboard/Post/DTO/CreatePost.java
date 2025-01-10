package cafeboard.Post.DTO;

public record CreatePost(
        String title,
        String content,
        Long boardId
) {
}
