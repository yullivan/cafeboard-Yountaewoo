package cafeboard;

import cafeboard.Board.Dto.CreateBoard;
import cafeboard.Board.Dto.UpdateBoard;
import cafeboard.Comment.DTO.*;
import cafeboard.Member.Dto.MemberResponse;
import cafeboard.Member.Dto.MemberResquest;
import cafeboard.Post.DTO.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;


//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class ApiTest {
//
//    @LocalServerPort
//    int port;
//
//    @BeforeEach
//    void setUp() {
//        RestAssured.port = port;
//    }
//
//    @Test
//    void 게시판ApiTest() {
//        //게시판 생성
//        RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new CreateBoard("자유게시판"))
//                .when()
//                .post("/boards")
//                .then().log().all()
//                .statusCode(200);
//
//        RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new CreateBoard("일일게시판"))
//                .when()
//                .post("/boards")
//                .then().log().all()
//                .statusCode(200);
//
//        //게시판 조회
//        RestAssured
//                .given().log().all()
//                .when()
//                .get("/boards")
//                .then().log().all()
//                .statusCode(200);
//
//        //게시판 수정
//        RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new UpdateBoard("수정한 자유게시판", 1L))
//                .put("/boards")
//                .then().log().all()
//                .statusCode(200);
//    }
//
//    @Test
//    void 게시글ApiTest() {
//        //게시판 생성
//        RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new CreateBoard("자유게시판"))
//                .when()
//                .post("/boards")
//                .then().log().all()
//                .statusCode(200); // 요청에 대한 서버 응답의 상태코드가 200인지 검증
//
//        // 게시글 생성
//        RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new PostRequest("점메추", "점심메뉴추천", 1L))
//                .when()
//                .post("/posts")
//                .then().log().all()
//                .statusCode(200);
//    }
//
//    @Test
//    void 댓글생성Test() {
//
//        //게시판 생성
//        RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new CreateBoard("자유게시판"))
//                .when()
//                .post("/boards")
//                .then().log().all()
//                .statusCode(200); // 요청에 대한 서버 응답의 상태코드가 200인지 검증
//
//        // 게시글 생성
//        RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new PostRequest("점메추", "점심메뉴추천", 1L))
//                .when()
//                .post("/posts")
//                .then().log().all()
//                .statusCode(200);
//
//
//        //댓글 생성
//        CreateCommentResponse createCommentResponse = RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new CreateComment("우와!", 1L))
//                .when()
//                .post("/comments")
//                .then().log().all()
//                .statusCode(200)
//                .extract()
//                .as(CreateCommentResponse.class);
//
//        assertThat(createCommentResponse.postId()).isEqualTo(1);
//
//    }
//
//    @Test
//    void 댓글수정Test() {
//        //게시판 생성
//        RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new CreateBoard("자유게시판"))
//                .when()
//                .post("/boards")
//                .then().log().all()
//                .statusCode(200); // 요청에 대한 서버 응답의 상태코드가 200인지 검증
//
//        // 게시글 생성
//        RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new PostRequest("점메추", "점심메뉴추천", 1L))
//                .when()
//                .post("/posts")
//                .then().log().all()
//                .statusCode(200);
//
//
//        //댓글 생성
//        CreateCommentResponse createCommentResponse = RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new CreateComment("우와!", 1L))
//                .when()
//                .post("/comments")
//                .then().log().all()
//                .statusCode(200)
//                .extract()
//                .as(CreateCommentResponse.class);
//
//        //댓글 수정
//        UpdateCommentResponse updateComment = RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new UpdateComment("와우!"))
//                .pathParam("commentId", 1l)
//                .when()
//                .put("/comments/{commentId}")
//                .then().log().all()
//                .statusCode(200)
//                .extract()
//                .as(UpdateCommentResponse.class);
//
//        assertThat(updateComment.content()).isEqualTo("와우!");
//
//    }
//
//    @Test
//    void 댓글삭제ApiTest() {
//        //게시판 생성
//        RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new CreateBoard("자유게시판"))
//                .when()
//                .post("/boards")
//                .then().log().all()
//                .statusCode(200); // 요청에 대한 서버 응답의 상태코드가 200인지 검증
//
//        // 게시글 생성
//        RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new PostRequest("점메추", "점심메뉴추천", 1L))
//                .when()
//                .post("/posts")
//                .then().log().all()
//                .statusCode(200);
//
//
//        //댓글 생성
//        CreateCommentResponse createCommentResponse = RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new CreateComment("우와!", 1L))
//                .when()
//                .post("/comments")
//                .then().log().all()
//                .statusCode(200)
//                .extract()
//                .as(CreateCommentResponse.class);
//
//        //댓글 삭제
//        RestAssured
//                .given().log().all()
//                .pathParam("commentId", 1L)
//                .when()
//                .delete("/comments/{commentId}")
//                .then().log().all()
//                .statusCode(200);
//    }
//
//    @Test
//    void 게시글상세조회ApiTest() {
//        //게시판 생성
//        RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new CreateBoard("자유게시판"))
//                .when()
//                .post("/boards")
//                .then().log().all()
//                .statusCode(200);
//
//        // 게시글 생성
//        RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new PostRequest("점메추", "점심메뉴추천", 1L))
//                .when()
//                .post("/posts")
//                .then().log().all()
//                .statusCode(200);
//
//        //댓글 생성
//        RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new CreateComment("우와!", 1L))
//                .when()
//                .post("/comments")
//                .then().log().all()
//                .statusCode(200);
//
//        //게시글 상세 조회
//        FindDetailPostResponse findDetailPostResponse = RestAssured
//                .given().log().all()
//                .pathParam("postId", 1L)
//                .when()
//                .get("/posts/{postId}")
//                .then().log().all()
//                .statusCode(200)
//                .extract()
//                .as(FindDetailPostResponse.class);
//
//        assertThat(findDetailPostResponse.comments().size()).isEqualTo(1);
//    }
//
//    @Test
//    void 게시글수정ApiTest() {
//        //게시판 생성
//        RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new CreateBoard("자유게시판"))
//                .when()
//                .post("/boards")
//                .then().log().all()
//                .statusCode(200);
//
//        // 게시글 생성
//        RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new PostRequest("점메추", "점심메뉴추천", 1L))
//                .when()
//                .post("/posts")
//                .then().log().all()
//                .statusCode(200);
//
//        //게시글 수정
//        UpdatePostResponse updatePostResponse = RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new PostDetailRequest("저메추", "저녁메뉴추천", 1L))
//                .when()
//                .put("/posts")
//                .then().log().all()
//                .statusCode(200)
//                .extract()
//                .as(UpdatePostResponse.class);
//
//        assertThat(updatePostResponse.content()).isEqualTo("저녁메뉴추천");
//    }
//
//    @Test
//    void 게시글삭제ApiTest() {
//        //게시판 생성
//        RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new CreateBoard("자유게시판"))
//                .when()
//                .post("/boards")
//                .then().log().all()
//                .statusCode(200);
//
//        // 게시글 생성
//        RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new PostRequest("점메추", "점심메뉴추천", 1L))
//                .when()
//                .post("/posts")
//                .then().log().all()
//                .statusCode(200);
//
//        //게시글 삭제
//        RestAssured
//                .given().log().all()
//                .pathParam("postId", 1L)
//                .when()
//                .delete("/posts/{postId}")
//                .then().log().all()
//                .statusCode(200);
//
//        //게시글 목록 조회
//        FindAllPostsResponse findAllPostsResponse = RestAssured
//                .given().log().all()
//                .when()
//                .get("/posts")
//                .then().log().all()
//                .statusCode(200)
//                .extract()
//                .as(FindAllPostsResponse.class);
//        assertThat(findAllPostsResponse.posts().size()).isEqualTo(0);
//    }
//
//
//    @Test
//    void 특정게시판의게시글목록조회ApiTest2() {
//
//        //게시판 생성
//        RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new CreateBoard("자유게시판"))
//                .when()
//                .post("/boards")
//                .then().log().all()
//                .statusCode(200);
//
//        // 게시글 생성
//        RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new PostRequest("점메추", "점심메뉴추천", 1L))
//                .when()
//                .post("/posts")
//                .then().log().all()
//                .statusCode(200);
//
//        //특정 게시판의 게시글 목록 조회
//        FindAllPostsResponse findAllPostsResponse = RestAssured
//                .given().log().all()
//                .pathParam("boardId", 1L)
//                .when()
//                .get("/posts/boards/{boardId}")
//                .then().log().all()
//                .statusCode(200)
//                .extract()
//                .as(FindAllPostsResponse.class);
//        assertThat(findAllPostsResponse.posts().size()).isEqualTo(1);
//    }
//
//    @Test
//    void 특정게시글의댓글목록조회ApiTest() {
//        //게시판 생성
//        RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new CreateBoard("자유게시판"))
//                .when()
//                .post("/boards")
//                .then().log().all()
//                .statusCode(200);
//
//        // 게시글 생성
//        RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new PostRequest("점메추", "점심메뉴추천", 1L))
//                .when()
//                .post("/posts")
//                .then().log().all()
//                .statusCode(200);
//
//        //댓글 생성
//        RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new CreateComment("우와!", 1L))
//                .when()
//                .post("/comments")
//                .then().log().all()
//                .statusCode(200);
//
//        //특정게시글의댓글목록조회
//        FindAllComment commentList = RestAssured
//                .given().log().all()
//                .pathParam("boardId", 1L)
//                .when()
//                .get("/comments/{boardId}")
//                .then().log().all()
//                .statusCode(200)
//                .extract()
//                .as(FindAllComment.class);
//        assertThat(commentList.comments().size()).isEqualTo(1);
//    }
//
//    @Test
//    void 회원가입ApiTest() {
//        MemberResponse member = RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new MemberResquest("윤태우", "123456", "123"))
//                .when()
//                .post("/members")
//                .then().log().all()
//                .statusCode(200)
//                .extract()
//                .as(MemberResponse.class);
//        assertThat(member.name()).isEqualTo("윤태우");
//    }
//
//    @Test
//    void 회원탈퇴ApiTest() {
//        RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new MemberResquest("윤태우", "123456", "123"))
//                .when()
//                .post("/members")
//                .then().log().all()
//                .statusCode(200);
//
//        RestAssured
//                .given().log().all()
//                .pathParam("memberId", 1L)
//                .when()
//                .delete("/members/{memberId}")
//                .then().log().all()
//                .statusCode(200);
//    }
//}