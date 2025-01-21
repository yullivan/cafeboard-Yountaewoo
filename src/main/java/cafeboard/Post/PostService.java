package cafeboard.Post;

import cafeboard.Board.Board;
import cafeboard.Board.BoardRepository;
import cafeboard.Comment.CommentRepository;
import cafeboard.Member.Member;
import cafeboard.Member.MemberRepository;
import cafeboard.Post.DTO.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    public PostService(PostRepository postRepository, BoardRepository boardRepository, CommentRepository commentRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
        this.memberRepository = memberRepository;
    }

    // 게시글 생성
    public PostDetailResponse createPost(PostRequest postRequest, String userId) {
        Board findBoard = boardRepository.findById(postRequest.boardId()).orElseThrow(
                () -> new NoSuchElementException("ID 를 찾을 수 없습니다:" + postRequest.boardId()));

        Member findMember = memberRepository.findByUserId(userId).orElseThrow(
                () -> new NoSuchElementException("ID 를 찾을 수 없습니다:" + userId));

        Post post = new Post(findBoard, postRequest.title(), postRequest.content(), findMember);
        postRepository.save(post);
        return new PostDetailResponse(post.getTitle(), post.getContent(), findBoard, post.getCreatedAt()
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
    public PostDetailResponse updateById(PostDetailRequest postDetailRequest, String userId) {
        Post findPost = postRepository.findById(postDetailRequest.postId()).orElseThrow(
                () -> new NoSuchElementException("ID 를 찾을 수 없습니다:" + postDetailRequest.postId()));

        memberRepository.findByUserId(userId).orElseThrow(
                () -> new NoSuchElementException("ID 를 찾을 수 없습니다:" + userId));


        //

        findPost.updateContent(postDetailRequest.content());

        findPost.updateTitle(postDetailRequest.title());

        return new PostDetailResponse(findPost.getTitle(), findPost.getContent(), findPost.getBoard()
                , findPost.getCreatedAt(), findPost.getId());
    }

    //게시글 삭제
    @Transactional
    public void deleteById(Long postId, String userId) {
        Post findPost = postRepository.findById(postId).orElseThrow(
                () -> new NoSuchElementException("ID 를 찾을 수 없습니다:" + postId));
        memberRepository.findByUserId(userId).orElseThrow(
                () -> new NoSuchElementException("ID 를 찾을 수 없습니다:" + userId));
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
