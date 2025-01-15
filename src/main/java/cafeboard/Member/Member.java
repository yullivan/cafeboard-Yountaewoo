package cafeboard.Member;

import cafeboard.Board.Board;
import cafeboard.Comment.Comment;
import cafeboard.Post.Post;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Member {

    private String userName;

    private String password;

    @Column(unique = true)
    private String userId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "member")
    private List<Comment> comments;

    @OneToMany(mappedBy = "member")
    private List<Post> posts;

    @CreatedDate
    private LocalDateTime createdDate;

    protected Member() {
    }

    public Member(String password, String userId, String userName) {
        this.password = password;
        this.userId = userId;
        this.userName = userName;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public String getPassword() {
        return password;
    }

    public String getUserId() {
        return userId;
    }

    public Long getId() {
        return id;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public String getUserName() {
        return userName;
    }
}
