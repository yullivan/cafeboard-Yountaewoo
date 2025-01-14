package cafeboard.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 특정 postId에 속한 모든 Comment 조회
    List<Comment> findByPostId(Long postId);
}
