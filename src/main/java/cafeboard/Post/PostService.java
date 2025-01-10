package cafeboard.Post;

import cafeboard.Board.Board;
import cafeboard.Board.BoardRepository;
import cafeboard.Board.Dto.CreateBoard;
import cafeboard.Board.Dto.CreateBoardResponse;
import cafeboard.Post.DTO.CreatePost;
import cafeboard.Post.DTO.CreatePostResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.NoSuchElementException;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;

    public PostService(PostRepository postRepository, BoardRepository boardRepository) {
        this.postRepository = postRepository;
        this.boardRepository = boardRepository;
    }

    // 게시글 생성
    public CreatePostResponse createPost(CreatePost createPost) {
        Board findBoard = boardRepository.findById(createPost.boardId()).orElseThrow(
                () -> new NoSuchElementException("ID 를 찾을 수 없습니다:" + createPost.boardId()));
        Post post = new Post(findBoard, createPost.title(), createPost.content());
        postRepository.save(post);
        return new CreatePostResponse(post.getTitle(), post.getContent(), findBoard, post.getCreatedTime()
                , post.getId());
    }
}
