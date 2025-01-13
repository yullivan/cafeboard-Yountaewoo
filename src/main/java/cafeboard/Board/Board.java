package cafeboard.Board;

import cafeboard.Member.Member;
import cafeboard.Post.Post;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Board {

    private String title;

    @OneToMany(mappedBy = "board")
    private List<Post> posts = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdTime;

    @LastModifiedDate
    private LocalDateTime updatedTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected Board() {
    }

    public Board(String title) {
        this.title = title;
    }

    public List<Post> getPosts() {
        return posts;
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
}
