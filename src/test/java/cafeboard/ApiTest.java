package cafeboard;

import cafeboard.Board.Dto.CreateBoard;
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

        RestAssured
                .given().log().all()
                .when()
                .get("/boards")
                .then().log().all()
                .statusCode(200); // 요청에 대한 서버 응답의 상태코드가 200인지 검증
    }
}