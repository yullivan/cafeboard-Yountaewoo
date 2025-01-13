package cafeboard.Post.DTO;

public record UpdatePost(
        String title,
        String content,
        Long id
) {
}
