package cafeboard.Comment;

import cafeboard.Comment.DTO.CreateComment;
import cafeboard.Comment.DTO.CreateCommentResponse;
import cafeboard.Post.Post;
import cafeboard.Post.PostRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    // 댓글 생성
    public void createComment(CreateComment createComment) {
        Post findPost = postRepository.findById(createComment.postId()).orElseThrow(
                () -> new NoSuchElementException("ID 를 찾을 수 없습니다:" + createComment.postId()));

        Comment comment = new Comment(createComment.content(), findPost);
        commentRepository.save(comment);

    }
}
