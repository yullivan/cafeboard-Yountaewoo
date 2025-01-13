package cafeboard.Post.DTO;

public record UpdatePostResponse(
        String title,
        String content,
        Long id
) {
}
