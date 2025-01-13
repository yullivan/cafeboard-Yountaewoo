package cafeboard.Comment;

import cafeboard.Comment.DTO.CreateComment;
import cafeboard.Comment.DTO.CreateCommentResponse;
import cafeboard.Comment.DTO.UpdateComment;
import cafeboard.Comment.DTO.UpdateCommentResponse;
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
}
