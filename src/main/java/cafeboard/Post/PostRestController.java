package cafeboard.Post;

import cafeboard.Post.DTO.*;
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

    //게시글 수정
    @PutMapping("/posts")
    public UpdatePostResponse updateById(@RequestBody UpdatePost updatePost) {
        return postService.updateById(updatePost);
    }

    //게시글 삭제
    @DeleteMapping("/posts/{postId}")
    public void deleteById(@PathVariable Long postId) {
        postService.deleteById(postId);
    }
}

