package cafeboard.Post.DTO;

public record PostRequest(
        String title,
        String content,
        Long boardId
) {
}
