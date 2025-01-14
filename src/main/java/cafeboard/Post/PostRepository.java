package cafeboard.Post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    // 특정 boardId에 속한 모든 Post 조회
    List<Post> findByBoardId(Long boardId);
}
