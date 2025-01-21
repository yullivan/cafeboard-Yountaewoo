package cafeboard.Post;

import cafeboard.Comment.QComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostQueryRepository {

    private final JPAQueryFactory queryFactory;

    private final QPost post = QPost.post;

    private final QComment comment = QComment.comment;

    public PostQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public List<Post> findLikeCountTop() {
        queryFactory.selectFrom(post)
                .
    }
}
