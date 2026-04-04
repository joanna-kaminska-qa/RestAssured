import models.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class CheckUsersParamTests extends BaseTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/check_users.csv", numLinesToSkip = 1)
    void testCheckUsers(int userId, String emailSuffix){

        List<User> users = given()
                .spec(BaseTest.requestSpec)
                .log().uri()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath().getList("", User.class);

        User user = users.stream()
                .filter(u -> u.getId() == (userId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        assertThat(user.getEmail(), containsString(emailSuffix));

        System.out.println("Verified user: " + user.getEmail());

    }
}
