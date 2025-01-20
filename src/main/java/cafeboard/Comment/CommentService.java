package cafeboard.Comment;

import cafeboard.Comment.DTO.*;
import cafeboard.Member.Member;
import cafeboard.Member.MemberRepository;
import cafeboard.Post.Post;
import cafeboard.Post.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, MemberRepository memberRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    // 댓글 생성
    public CreateCommentResponse createComment(CreateComment createComment, String userId) {
        Post findPost = postRepository.findById(createComment.postId()).orElseThrow(
                () -> new NoSuchElementException("ID 를 찾을 수 없습니다:" + createComment.postId()));
        Member findMember = memberRepository.findByUserId(userId).orElseThrow(
                () -> new NoSuchElementException("사용자를 찾을 수 없습니다."));
        Comment comment = new Comment(createComment.content(), findMember, findPost);
        commentRepository.save(comment);
        return new CreateCommentResponse(comment.getContent(), comment.getId());
    }

    //댓글 수정
    @Transactional
    public UpdateCommentResponse updateComment(Long commentId, UpdateComment updateComment, String userId) {
        Comment findComment = commentRepository.findById(commentId).orElseThrow(
                () -> new NoSuchElementException("ID 를 찾을 수 없습니다:" + commentId));
        Member findMember = memberRepository.findByUserId(userId).orElseThrow(
                () -> new NoSuchElementException("사용자를 찾을 수 없습니다."));
        findComment.updateContent(updateComment.content());
        return new UpdateCommentResponse(findComment.getContent(), findComment.getId());
    }

    //댓글 삭제
    @Transactional
    public void deleteComment(Long commentId, String userId) {
        Comment findComment = commentRepository.findById(commentId).orElseThrow(
                () -> new NoSuchElementException("ID 를 찾을 수 없습니다:" + commentId));
        Member findMember = memberRepository.findByUserId(userId).orElseThrow(
                () -> new NoSuchElementException("사용자를 찾을 수 없습니다."));
        commentRepository.delete(findComment);
    }

    //특정 게시글의 댓글 목록 조회
    public FindAllComment findByPostId(Long postId) {
        List<FindAllComment.Comment> comments = commentRepository.findByPostId(postId)
                .stream()
                .map(comment -> new FindAllComment.Comment(comment.getContent(), comment.getId()))
                .toList();
        return new FindAllComment(comments);
    }
}
