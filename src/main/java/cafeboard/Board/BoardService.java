package cafeboard.Board;

import cafeboard.Board.Dto.CreateBoard;
import cafeboard.Board.Dto.CreateBoardResponse;
import cafeboard.Board.Dto.FindAllResponse;
import cafeboard.Board.Dto.UpdateBoard;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 게시판 생성
    public CreateBoardResponse createBoard(CreateBoard createBoard) {
        Board board = new Board(createBoard.title());
        boardRepository.save(board);
        return new CreateBoardResponse(board.getTitle(), board.getCreatedTime(), board.getId());
    }

    // 게시판 모두 조회
    public List<FindAllResponse> findAll() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(board -> new FindAllResponse(board.getTitle(), board.getId()))
                .toList();
    }

    // 게시판 수정
    @Transactional
    public UpdateBoard updateBoard(UpdateBoard updateBoard) {
        Board findBoard = boardRepository.findById(updateBoard.id()).orElseThrow(
                () -> new NoSuchElementException("ID를 찾을 수 없습니다:" + updateBoard.id()));
        findBoard.updateTitle(updateBoard.title());
        return new UpdateBoard(findBoard.getTitle(), findBoard.getId());
    }
}
