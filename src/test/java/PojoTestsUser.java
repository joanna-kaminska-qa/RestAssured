import models.User;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


class PojoTestsUser extends BaseTest{

    @Test
    void testGetUserToPojo(){

        User responseUser = given()
                .spec(BaseTest.requestSpec)
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .extract()
                .as(User.class);

        assertEquals(1, responseUser.getId());
        assertEquals("Leanne Graham", responseUser.getName());
        assertEquals("Bret", responseUser.getUsername());
    }
}
