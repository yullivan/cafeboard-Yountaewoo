package cafeboard.Board;

import cafeboard.Board.Dto.CreateBoard;
import cafeboard.Board.Dto.CreateBoardResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardRestController {

    private final BoardService boardService;

    public BoardRestController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 게시판 생성
    @PostMapping("/boards")
    public CreateBoardResponse createBoard(@RequestBody CreateBoard createBoard) {
        return boardService.createBoard(createBoard);
    }
}
