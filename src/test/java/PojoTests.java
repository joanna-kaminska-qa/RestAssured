import models.Post;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PojoTests extends BaseTest {

    @Test
    void testCreatePostWithPojo() {
        // Zamiast Mapy, tworzymy obiekt naszej klasy
        Post newPost = new Post(1, "Tytuł z POJO", "Treść z POJO");

        given()
                .spec(BaseTest.requestSpec)
                .body(newPost) // RestAssured sam zamieni ten obiekt na JSON!
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo(newPost.getTitle()))
                .log().all();
    }

    @Test
    void testGetPostAndMapToPojo() {
        Post responsePost = given()
                .spec(BaseTest.requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .extract()
                .as(Post.class);

        // Teraz możesz używać metod Javy zamiast ścieżek JSONowych
        System.out.println("Tytuł posta to: " + responsePost.getTitle());
        assertEquals(1, responsePost.getId()); // Używamy asercji Junit
    }
}
