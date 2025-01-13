package cafeboard.Comment;

import cafeboard.Member.Member;
import cafeboard.Post.Post;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    private String content;

    @CreatedDate
    LocalDateTime createdTime;

    @LastModifiedDate
    LocalDateTime updatedTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Post post;

    @ManyToOne
    private Member member;

    protected Comment() {
    }

    public Comment(String content, Post post) {
        this.content = content;
        this.post = post;
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

    public Post getPost() {
        return post;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
