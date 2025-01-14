package cafeboard.Comment;

import cafeboard.Comment.DTO.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentRestController {
    private final CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 생성
    @PostMapping("/comments")
    public CreateCommentResponse createComment(@RequestBody CreateComment createComment) {
        return commentService.createComment(createComment);
    }

    //댓글 수정
    @PutMapping("/comments/{commentId}")
    public UpdateCommentResponse updateComment(@PathVariable Long commentId, @RequestBody UpdateComment updateComment) {
        return commentService.updateComment(commentId, updateComment);
    }

    //댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }

    //특정 게시글의 댓글 목록 조회
    @GetMapping("/comments/{postId}")
    public FindAllComment findByPostId(@PathVariable Long postId) {
        return commentService.findByPostId(postId);
    }
}
