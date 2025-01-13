package cafeboard.Post;

import cafeboard.Board.Board;
import cafeboard.Comment.Comment;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Post {

    private String title;

    private String content;

    @CreatedDate
    private LocalDateTime createdTime;

    @LastModifiedDate
    private LocalDateTime updatedTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Board board;

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

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
