package cafeboard.Board;

import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.file.LinkOption;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
