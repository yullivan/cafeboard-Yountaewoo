package cafeboard.Post.DTO;

import cafeboard.Post.Post;

import java.util.List;

public record FindAllPostsResponse(
        List<Post> posts
) {
    public record Post(
            Long postId,
            int commentCount,
            String title
    ) {
    }
}
