import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AuthTests extends BaseTest {

    @Test
    void testWithAuth() {
        given()
                .auth().oauth2("jakis-twój-tekst")
                .spec(BaseTest.requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .spec(BaseTest.responseSpec)
                .log().all();
    }

    @Test
    void testWithAuthToken() {

        String token = "my_secret_token_2026";

        given()
                .auth().oauth2(token)
                .spec(BaseTest.requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .spec(BaseTest.responseSpec)
                .log().all();
    }

    @Test
    void testWithBasicAuth() {
        given()
                .auth().preemptive().basic("admin", "p@ssword123") // wymuszamy wysyłkę hasła nawet gdy system go nie wymaga (opcjonalne). Można też bez tego, wtedy bez preemptive()
                .spec(BaseTest.requestSpec)
                .log().all()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .spec(BaseTest.responseSpec)
                .log().all();
    }

    @Test
    void testWithApiKey() {
        given()
                .header("X-API-KEY", "secret-key-999") // Tak wysyłamy API Key
                .spec(BaseTest.requestSpec)
                .log().headers() // Wypisz tylko nagłówki, żeby było czytelnie
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200);
    }

    @Test
    void testWithApiKeyInUrl() {
        given()
                .queryParam("api_key", "moj_klucz_123") // Tak doklejamy klucz do URL
                .spec(BaseTest.requestSpec)
                .log().uri() // Logujemy tylko URI, żeby zobaczyć jak się zmienił
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200);
    }
}
