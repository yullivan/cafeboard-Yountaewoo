package cafeboard;

import cafeboard.Board.Dto.CreateBoard;
import cafeboard.Board.Dto.UpdateBoard;
import cafeboard.Post.DTO.CreatePost;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 상품목록조회() {
        //게시판 생성
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new CreateBoard("자유게시판"))
                .when()
                .post("/boards")
                .then().log().all()
                .statusCode(200); // 요청에 대한 서버 응답의 상태코드가 200인지 검증

        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new CreateBoard("일일게시판"))
                .when()
                .post("/boards")
                .then().log().all()
                .statusCode(200); // 요청에 대한 서버 응답의 상태코드가 200인지 검증

        //게시판 조회
        RestAssured
                .given().log().all()
                .when()
                .get("/boards")
                .then().log().all()
                .statusCode(200); // 요청에 대한 서버 응답의 상태코드가 200인지 검증

        //게시판 수정
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new UpdateBoard("수정한 자유게시판", 1L))
                .put("/boards")
                .then().log().all()
                .statusCode(200);

        //게시판 삭제
        RestAssured
                .given().log().all()
                .pathParam("boardId", 1L)
                .when()
                .delete("/boards/{boardId}")
                .then().log().all()
                .statusCode(200);

        //삭제 되었는지 조회
        RestAssured
                .given().log().all()
                .when()
                .get("/boards")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    void 게시글테스트() {
        //게시판 생성
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new CreateBoard("자유게시판"))
                .when()
                .post("/boards")
                .then().log().all()
                .statusCode(200); // 요청에 대한 서버 응답의 상태코드가 200인지 검증

        // 게시글 생성
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new CreatePost("점메추", "점심메뉴추천", 1L))
                .when()
                .post("/posts")
                .then().log().all()
                .statusCode(200);
    }
}