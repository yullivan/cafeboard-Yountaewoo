package cafeboard.Board;

import cafeboard.Board.Dto.CreateBoard;
import cafeboard.Board.Dto.CreateBoardResponse;
import cafeboard.Board.Dto.FindAllResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    // 게시판 모두 조회
    @GetMapping("/boards")
    public List<FindAllResponse> findAll() {
        return boardService.findAll();
    }
}
