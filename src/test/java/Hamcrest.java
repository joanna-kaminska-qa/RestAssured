import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import models.Post;
import models.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Hamcrest extends BaseTest {

    @Test
    void testGetPostAndCheckWithHamcrest() {
        User responseUser = given()
                .spec(BaseTest.requestSpec)
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .extract()
                .as(User.class);

        assertThat(responseUser.getId(), is(1));
        assertThat(responseUser.getName(), containsString("Leanne"));
        assertThat(responseUser.getUsername(), equalToIgnoringCase("bret"));
        assertThat(responseUser.getName(), containsString("Graham"));
        assertThat(responseUser.getEmail(), endsWith(".biz"));
    }

    @Test
    void testAllUsersWithHamcrest() {
        List<User> users = given()
                .spec(BaseTest.requestSpec)
                .when()
                .get("/users") // Pobieramy WSZYSTKICH
                .then()
                .statusCode(200)
                .extract()
                .jsonPath().getList("", User.class); // "Wlej" wszystkich do listy. Nie musisz "wchodzić głębiej" w żadną gałąź. Puste "" mówi: "Zacznij mapować obiekty User od samego początku (od korzenia) tego, co dostałeś".

        assertThat(users, hasSize(10));
        assertThat(users.get(0).getName(), is("Leanne Graham")); // Sprawdź pierwszego z listy
    }

    @Test
    void testSearchUserWithStream() {
        List<User> users = given()
                .spec(BaseTest.requestSpec)
                .when()
                .get("/users")
                .then()
                .extract()
                .jsonPath().getList("", User.class);

        // Szukamy Samanthy w "strumieniu" danych
        User samantha = users.stream()
                .filter(u -> u.getUsername().equals("Samantha")) // Filtrujemy
                .findFirst() // Bierzemy pierwszą pasującą
                .orElseThrow(); // Albo rzuć błędem, jeśli jej nie ma

        // Asercja Hamcrest
        assertThat(samantha.getName(), is("Clementine Bauch"));
    }

    @Test
    void testSearchPostWithStream() {
        List<Post> posts = given()
                .spec(BaseTest.requestSpec)
                .when()
                .get("/posts")
                .then()
                .extract()
                .jsonPath().getList("", Post.class);

        assertThat(posts, hasSize(100));

        Post post = posts.stream()
                .filter(p -> p.getId().equals(55))
                .findFirst()
                .orElseThrow();

        assertThat(post.getUserId(), is(6));
        assertThat(post.getTitle(), containsString("voluptat"));
    }

    @Test
    void testSearchUsersWithMachers() {
        List<User> user = given()
                .spec(BaseTest.requestSpec)
                .when()
                .get("/users")
                .then()
                .extract()
                .jsonPath().getList("", User.class);

        assertThat(user, hasItem(hasProperty("email", is("Sincere@april.biz"))));
    }
}

