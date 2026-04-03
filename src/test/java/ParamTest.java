import static io.restassured.RestAssured.given;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ParamTest extends BaseTest{

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10}) // To są Twoje "wartości"
    void testManyPosts(int postId) { // postId to "nazwa" Twojej zmiennej, będą pod nią po kolei podkładane wartości z ValueSource
        given()
                .spec(BaseTest.requestSpec)
                .when()
                .get("/posts/" + postId) // Tu podstawią się po kolei: 1, 5, 10
                .then()
                .statusCode(200)
                .log().body();
    }

    @ParameterizedTest
    @ValueSource(strings = {"token_pl", "token_uk", "token_de"})
    void testMStringToken (String token) {
        given()
                .auth().oauth2(token)
                .queryParam("authuser", "0")
                .spec(BaseTest.requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .log().all();
    }
}
