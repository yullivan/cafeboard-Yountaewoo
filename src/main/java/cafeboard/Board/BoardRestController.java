package cafeboard.Board;

import cafeboard.Board.Dto.*;
import org.springframework.web.bind.annotation.*;

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

    //게시판 수정
    @PutMapping("/boards")
    public UpdateBoard updateBoard(@RequestBody UpdateBoard updateBoard) {
        return boardService.updateBoard(updateBoard);
    }

    // 게시판 삭제
    @DeleteMapping("/boards/{boardId}")
    public void deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
    }
}
