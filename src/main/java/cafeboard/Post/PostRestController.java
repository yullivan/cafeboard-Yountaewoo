package cafeboard.Post;

import cafeboard.Post.DTO.CreatePost;
import cafeboard.Post.DTO.CreatePostResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostRestController {

    private final PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 생성
    @PostMapping("/posts")
    public CreatePostResponse createPost(@RequestBody CreatePost createPost) {
        return postService.createPost(createPost);
    }
}
