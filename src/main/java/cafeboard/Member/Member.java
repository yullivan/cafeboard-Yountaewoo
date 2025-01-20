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
import java.util.NoSuchElementException;

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

    //입력 받은 비밀번호와 저장된 비밀번호 조회
    public void findByPassword(String password) {
        if (!this.getPassword().equals(SecurityUtils.sha256EncryptHex2(password))) {
            throw new NoSuchElementException("해당하는 유저가 없습니다");
        }
    }
}
