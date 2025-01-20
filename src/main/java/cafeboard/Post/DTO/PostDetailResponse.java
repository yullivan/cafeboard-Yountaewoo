package cafeboard.Post.DTO;

import cafeboard.Board.Board;

import java.time.LocalDateTime;

public record PostDetailResponse(
        String title,
        String content,
        Board board,
        LocalDateTime createdTime,
        Long id
) {
}
