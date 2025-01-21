package cafeboard.Comment;

import cafeboard.Comment.DTO.*;
import cafeboard.Member.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentRestController {
    private final CommentService commentService;
    private final MemberService memberService;

    public CommentRestController(CommentService commentService, MemberService memberService) {
        this.commentService = commentService;
        this.memberService = memberService;
    }

    // 댓글 생성
    @PostMapping("/comments")
    public CreateCommentResponse createComment(@RequestBody CreateComment createComment,
                                               @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        String userId = memberService.getProfile(authorization);
        return commentService.createComment(createComment, userId);
    }

    //댓글 수정
    @PutMapping("/comments/{commentId}")
    public UpdateCommentResponse updateComment(@PathVariable Long commentId, @RequestBody UpdateComment updateComment,
                                               @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        String userId = memberService.getProfile(authorization);
        return commentService.updateComment(commentId, updateComment, userId);
    }

    //댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public void deleteComment(@PathVariable Long commentId,
                              @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        String userId = memberService.getProfile(authorization);
        commentService.deleteComment(commentId, userId);
    }

    //특정 게시글의 댓글 목록 조회
    @GetMapping("/comments/{postId}")
    public FindAllComment findByPostId(@PathVariable Long postId) {
        return commentService.findByPostId(postId);
    }
}
