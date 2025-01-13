package cafeboard.Board.Dto;

import java.util.List;

public record FindDetailBoardResponse(
        List<Post> posts
) {
    public record Post(
            String title,
            Long id
    ) {
    }
}
