package cafeboard.Post;

import cafeboard.BaseEntity;
import cafeboard.Board.Board;
import cafeboard.Comment.Comment;
import cafeboard.Member.Member;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Board board;

    @ManyToOne
    private Member member;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    protected Post() {
    }

    public Post(Board board, String title, String content) {
        this.board = board;
        this.title = title;
        this.content = content;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Board getBoard() {
        return board;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return super.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getUpdatedAt() {
        return super.getUpdatedAt();
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
