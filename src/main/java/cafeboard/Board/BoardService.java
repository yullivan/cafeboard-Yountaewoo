package cafeboard.Board;

import cafeboard.Board.Dto.*;
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

    // 게시판 삭제
    public void deleteBoard(Long boardId) {
        Board findBoard = boardRepository.findById(boardId).orElseThrow(
                () -> new NoSuchElementException("ID를 찾을 수 없습니다:" + boardId));
        boardRepository.delete(findBoard);
    }

    //특정 게시판의 게시글 목록 조회
    public FindDetailBoardResponse findById(Long boardId) {
        Board findBoard = boardRepository.findById(boardId).orElseThrow(
                () -> new NoSuchElementException("ID를 찾을 수 없습니다:" + boardId));
        List<FindDetailBoardResponse.Post> posts = findBoard.getPosts()
                .stream()
                .map(post -> new FindDetailBoardResponse.Post(post.getTitle(), post.getId()))
                .toList();
        return new FindDetailBoardResponse(posts);
    }
}
