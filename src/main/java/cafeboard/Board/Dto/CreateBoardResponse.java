package cafeboard.Board.Dto;

import java.time.LocalDateTime;

public record CreateBoardResponse(
        String title,
        LocalDateTime createdTime,
        Long id
) {
}
