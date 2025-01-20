package cafeboard.Member;

import cafeboard.BaseEntity;
import cafeboard.Board.Board;
import cafeboard.Comment.Comment;
import cafeboard.Post.Post;
import cafeboard.SecurityUtils;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Member extends BaseEntity {

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String userId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "member")
    private List<Comment> comments;

    @OneToMany(mappedBy = "member")
    private List<Post> posts;

    protected Member() {
    }

    public Member(String password, String userId, String userName) {
        this.password = SecurityUtils.sha256EncryptHex2(password);
        this.userId = userId;
        this.userName = userName;
    }

    public List<Comment> getComments() {
        return comments;
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
