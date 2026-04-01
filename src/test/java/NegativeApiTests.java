import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class NegativeApiTests extends BaseTest {

    @Test
    void testGetNonExistingPost() {
        given()
                .spec(requestSpec)
                .when()
                .get("/posts/9999")
                .then()
                .statusCode(404)
                .spec(responseSpec)
                .log().all();
    }

    @Test
    void testCreatePostWithInvalidBody() {
        given()
                .spec(requestSpec)
                .body("{ \"title\": 123 }") // Wysyłamy liczbę tam, gdzie powinien być tekst
                .when()
                .post("/posts")
                .then()
                .statusCode(201) // normalnie powinno być 400 - bad request, ale to treningowe API przyjmuje wszystko xd
                .spec(responseSpec)
                .log().all();
    }

    @Test
    void testInvalidEndpoint() {
        given()
                .spec(requestSpec)
                .when()
                .get("/posty-ktorych-nie-ma") // Literówka: zamiast /posts dajesz coś innego
                .then()
                .statusCode(404)
                .spec(responseSpec)
                .log().all();
    }
}
