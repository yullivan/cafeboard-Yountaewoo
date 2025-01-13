package cafeboard.Comment;

import cafeboard.Comment.DTO.CreateComment;
import cafeboard.Comment.DTO.CreateCommentResponse;
import cafeboard.Comment.DTO.UpdateComment;
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
    public void updateComment(@PathVariable Long commentId, @RequestBody UpdateComment updateComment) {
        commentService.updateComment(commentId, updateComment);
    }
}
