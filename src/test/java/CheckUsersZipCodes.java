import models.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.startsWith;

public class CheckUsersZipCodes extends BaseTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/city_zipcodes.csv", numLinesToSkip = 1)
    void testCheckUsers(String city, String zipCodePrefix){

        List<User> users = given()
                .spec(BaseTest.requestSpec)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath().getList("", User.class);

        User user = users.stream()
                .filter(u -> u.getAddress().getCity().equals(city))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("City not found: " + city));

        assertThat(user.getAddress().getZipcode(), startsWith(zipCodePrefix));

        System.out.println("City: " + city + " has ZIP: " + user.getAddress().getZipcode());
    }
}
