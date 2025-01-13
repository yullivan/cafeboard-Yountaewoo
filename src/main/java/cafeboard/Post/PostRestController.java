package cafeboard.Post;

import cafeboard.Post.DTO.CreatePost;
import cafeboard.Post.DTO.CreatePostResponse;
import cafeboard.Post.DTO.FindAllPostsResponse;
import org.springframework.web.bind.annotation.GetMapping;
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

    //게시글 목록 조회
    @GetMapping("/posts")
    public FindAllPostsResponse findAll() {
        return postService.findAll();
    }
}

