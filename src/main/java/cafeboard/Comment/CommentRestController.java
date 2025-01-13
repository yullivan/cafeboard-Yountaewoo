package cafeboard.Comment;

import cafeboard.Comment.DTO.CreateComment;
import cafeboard.Comment.DTO.CreateCommentResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentRestController {
    private final CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 생성
    @PostMapping("/comments")
    public void createComment(@RequestBody CreateComment createComment) {
        commentService.createComment(createComment);
    }
}
