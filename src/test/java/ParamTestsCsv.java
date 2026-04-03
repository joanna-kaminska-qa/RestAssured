import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;

public class ParamTestsCsv extends BaseTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/users.csv", numLinesToSkip = 1)
    void testUsersFromFile(int postId, String token, String authUser) {
        given()
                .auth().oauth2(token)
                .queryParam("authuser", authUser)
                .spec(BaseTest.requestSpec)
                .when()
                .get("/posts/" + postId)
                .then()
                .statusCode(200)
                .log().all();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/users2.csv", numLinesToSkip = 0)
    void testUsersFromFile2(int postId, String token, int code) {
        given()
                .auth().oauth2(token)
                .spec(BaseTest.requestSpec)
                .when()
                .get("/posts/" + postId)
                .then()
                .statusCode(200)
                .log().all();
    }
}
