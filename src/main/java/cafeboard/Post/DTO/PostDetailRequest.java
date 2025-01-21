package cafeboard.Post.DTO;

public record PostDetailRequest(
        String title,
        String content,
        Long postId,
        Long memberId
) {
}
