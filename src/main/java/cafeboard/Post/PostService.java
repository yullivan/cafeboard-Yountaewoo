package cafeboard.Post;

import cafeboard.Board.Board;
import cafeboard.Board.BoardRepository;
import cafeboard.Board.Dto.CreateBoard;
import cafeboard.Board.Dto.CreateBoardResponse;
import cafeboard.Comment.CommentRepository;
import cafeboard.Post.DTO.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public PostService(PostRepository postRepository, BoardRepository boardRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
    }

    // 게시글 생성
    public CreatePostResponse createPost(CreatePost createPost) {
        Board findBoard = boardRepository.findById(createPost.boardId()).orElseThrow(
                () -> new NoSuchElementException("ID 를 찾을 수 없습니다:" + createPost.boardId()));
        Post post = new Post(findBoard, createPost.title(), createPost.content());
        postRepository.save(post);
        return new CreatePostResponse(post.getTitle(), post.getContent(), findBoard, post.getCreatedAt()
                , post.getId());
    }

    //게시글 목록 조회
    public FindAllPostsResponse findAll() {
        return new FindAllPostsResponse(postRepository.findAll()
                .stream()
                .map(post -> new FindAllPostsResponse.PostResponse(post.getId(), post.getComments().size(), post.getTitle()))
                .toList());
    }

    //게시글 상세 조회
    public FindDetailPostResponse findById(Long postId) {
        Post findPost = postRepository.findById(postId).orElseThrow(
                () -> new NoSuchElementException("ID 를 찾을 수 없습니다:" + postId));
        List<FindDetailPostResponse.CommentResponse> comments = findPost.getComments()
                .stream()
                .map(comment -> new FindDetailPostResponse.CommentResponse(comment.getContent()))
                .toList();
        return new FindDetailPostResponse(comments, findPost.getContent());
    }

    //게시글 수정
    @Transactional
    public UpdatePostResponse updateById(UpdatePost updatePost) {
        Post findPost = postRepository.findById(updatePost.id()).orElseThrow(
                () -> new NoSuchElementException("ID 를 찾을 수 없습니다:" + updatePost.id()));
        findPost.updateContent(updatePost.content());
        findPost.updateTitle(updatePost.title());
        return new UpdatePostResponse(findPost.getTitle(), findPost.getContent(), findPost.getId());
    }

    //게시글 삭제
    @Transactional
    public void deleteById(Long postId) {
        Post findPost = postRepository.findById(postId).orElseThrow(
                () -> new NoSuchElementException("ID 를 찾을 수 없습니다:" + postId));
        commentRepository.deleteByPostId(postId);
        postRepository.delete(findPost);
    }

    //특정 게시판의 게시글 목록 조회
    public FindAllPostsResponse findByBoardId(Long boardId) {
        List<FindAllPostsResponse.PostResponse> list = postRepository.findByBoardId(boardId)
                .stream()
                .map(post -> new FindAllPostsResponse.PostResponse(post.getId(), post.getComments().size(), post.getTitle()))
                .toList();
        FindAllPostsResponse response = new FindAllPostsResponse(list);
        return response;
    }
}
