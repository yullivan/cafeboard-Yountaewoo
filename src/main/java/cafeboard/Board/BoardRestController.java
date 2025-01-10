package cafeboard.Board;

import cafeboard.Board.Dto.CreateBoard;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardRestController {

    private final BoardService boardService;

    public BoardRestController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 게시판 생성
    @PostMapping("/boards")
    public void createBoard(CreateBoard createBoard) {
        boardService.createBoard(createBoard);
    }
}
