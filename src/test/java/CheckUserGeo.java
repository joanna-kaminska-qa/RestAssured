import models.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

public class CheckUserGeo extends BaseTest {

    @Test
    void CheckGeo() {

        List<User> users = given()
                .spec(BaseTest.requestSpec)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath().getList("", User.class);

        User user = users.stream()
                .filter(u -> u.getName().equals("Clementina DuBuque"))
                .findFirst()
                .orElseThrow();

        String userLat = user.getAddress().getGeo().getLat();

        Double Lat = Double.parseDouble(userLat);

        assertThat(Lat, lessThan(0.0));

    }
}
