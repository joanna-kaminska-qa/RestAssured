import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;


public class ApiTest extends BaseTest {

    @Test
    void testConnection() {
        given()
                .spec(requestSpec)
                .queryParam("userId", 1)
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("[0].userId", equalTo(1))
                .body("[0].id", equalTo(1))
                .spec(responseSpec)
                .log().all();
    }

    @Test
    void testCreatePost() {
        // 1. Przygotowujemy dane, które chcemy wysłać
        Map<String, Object> bodyParams = new HashMap<>();
        bodyParams.put("title", "Moja nowa przygoda w OChK");
        bodyParams.put("body", "Nauka API idzie świetnie!");
        bodyParams.put("userId", 1);

        given()
                .spec(requestSpec)
                .body(bodyParams) // Tutaj wkładamy nasze dane
                .when()
                .post("/posts") // Zmieniamy metodę na POST!
                .then()
                .statusCode(201) // Sprawdzamy, czy serwer potwierdził stworzenie (201)
                .body("title", equalTo("Moja nowa przygoda w OChK"))
                .spec(responseSpec)
                .log().all();
    }

    @Test
    void testUpdatePost() {
        Map<String, Object> updateParams = new HashMap<>();
        updateParams.put("id", 1); // Niektóre API wymagają ID też w body
        updateParams.put("title", "Tytuł po edycji");
        updateParams.put("body", "Zmieniłam treść, bo mogę!");
        updateParams.put("userId", 1);

        given()
                .spec(requestSpec)
                .body(updateParams)
                .when()
                .put("/posts/1") // Tu celujemy w konkretny zasób!
                .then()
                .statusCode(200) // Przy edycji zazwyczaj wraca 200
                .body("title", equalTo("Tytuł po edycji"))
                .spec(responseSpec)
                .log().all();
    }

    @Test
    void testDeletePost() {
        given()
                .spec(requestSpec)
                .when()
                .delete("/posts/1") // Celujemy w konkretny post
                .then()
                .statusCode(200) // JSONPlaceholder zwraca 200, czasem API zwracają 204 (No Content)
                .spec(responseSpec)
                .log().all();
    }
}