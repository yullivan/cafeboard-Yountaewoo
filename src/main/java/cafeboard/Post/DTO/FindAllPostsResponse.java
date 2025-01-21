package cafeboard.Post.DTO;

import cafeboard.Post.Post;

import java.util.List;

public record FindAllPostsResponse(
        List<PostResponse> posts
) {
    public record PostResponse(
            Long postId,
            int commentCount,
            String title
    ) {
    }
}
