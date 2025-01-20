package cafeboard.Post;

import cafeboard.Member.MemberService;
import cafeboard.Post.DTO.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostRestController {

    private final PostService postService;
    private final MemberService memberService;

    public PostRestController(PostService postService, MemberService memberService) {
        this.postService = postService;
        this.memberService = memberService;
    }

    // 게시글 생성
    @PostMapping("/posts")
    public PostDetailResponse createPost(@RequestBody PostRequest createPost,
                                         @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        String userId = memberService.getProfile(authorization);
        return postService.createPost(createPost, userId);
    }

    //게시글 목록 조회
    @GetMapping("/posts")
    public FindAllPostsResponse findAll() {
        return postService.findAll();
    }

    //게시글 상세 조회
    @GetMapping("/posts/{postId}")
    public FindDetailPostResponse findById(@PathVariable Long postId) {
        return postService.findById(postId);
    }

    //게시글 수정
    @PutMapping("/posts")
    public PostDetailResponse updateById(@RequestBody PostDetailRequest updatePost,
                                         @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        String userId = memberService.getProfile(authorization);
        return postService.updateById(updatePost, userId);
    }

    //게시글 삭제
    @DeleteMapping("/posts/{postId}")
    public void deleteById(@PathVariable Long postId, HttpServletRequest httpServletRequest,
                           @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        String userId = memberService.getProfile(authorization);
        postService.deleteById(postId, userId);
    }

    //특정 게시판의 게시글 목록 조회
    @GetMapping("/posts/boards/{boardId}")
    public FindAllPostsResponse findByBoardId(@PathVariable Long boardId) {
        return postService.findByBoardId(boardId);
    }
}

