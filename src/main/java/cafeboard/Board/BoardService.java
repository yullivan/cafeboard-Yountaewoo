package cafeboard.Board;

import cafeboard.Board.Dto.CreateBoard;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 게시판 생성
    public void createBoard(CreateBoard createBoard) {
        Board board = new Board(createBoard.title());
        boardRepository.save(board);
    }
}
