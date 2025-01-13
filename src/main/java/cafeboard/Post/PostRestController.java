package cafeboard.Post;

import cafeboard.Post.DTO.CreatePost;
import cafeboard.Post.DTO.CreatePostResponse;
import cafeboard.Post.DTO.FindAllPostsResponse;
import cafeboard.Post.DTO.FindDetailPostResponse;
import org.springframework.web.bind.annotation.*;

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

    //게시글 목록 조회
    @GetMapping("/posts")
    public FindAllPostsResponse findAll() {
        return postService.findAll();
    }

    //게시글 살세 조회
    @GetMapping("/posts/{postId}")
    public FindDetailPostResponse findById(@PathVariable Long postId) {
        return postService.findById(postId);
    }
}

