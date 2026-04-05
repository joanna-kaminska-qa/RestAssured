import models.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.not;

public class CheckCompany extends BaseTest {

    @Test
    void testUserCompanyAndWebsite() {

        List<User> users = given()
                .spec(BaseTest.requestSpec)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath().getList("", User.class);

        User user = users.stream()
                .filter(u -> u.getCompany().getName().equals("Romaguera-Crona"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Company not found!"));

        assertThat(user.getWebsite(), not(endsWith(".com")));

    }
}
