package by.megatop.avchinnikova.alina.TestAPI;

import by.megatop.avchinnikova.alina.PostObject;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestTest {

    @Ignore
    @Test
    public void testGetLogin() {
        given().when().get("https://my.megatop.by/login").then();
        Assert.fail("Нечего не проверяет");
    }

    @Test
    public void testPostFromWithEmptyField() {
        PostObject po = new PostObject("", "");
        ValidatableResponse response = given().header("Content-Type", "application/json").body(po).
                when().post("https://admin.megatop.by/api/v1/user/login").then();
        Assert.assertEquals(response.extract().statusCode(), 500);
    }

    @Test
    public void testPostFromWithEmptyEmail() {
        PostObject po = new PostObject("", "qwerty1Q");
        ValidatableResponse response = given().header("Content-Type", "application/json").body(po).
                when().post("https://admin.megatop.by/api/v1/user/login").then();
        Assert.assertEquals(response.extract().statusCode(), 500);
    }

    @Test
    public void testPostFromWithEmptyPassword() {
        PostObject po = new PostObject("447858875", "");
        given().header("Content-Type", "application/json").body(po).
                when().post("https://admin.megatop.by/api/v1/user/login").then().
                assertThat().
                statusCode(422).
                body("message", equalTo("Вы ввели неверный номер телефона и/или пароль")).
                body("errors.phone[0]", equalTo("Вы ввели неверный номер телефона и/или пароль")).
                body("status", equalTo("error"));
    }

    @Test
    public void testPostFromWithIncorrectEmail() {
        PostObject po = new PostObject("117858875", "qwerty1Q");
        given().header("Content-Type", "application/json").body(po).
                when().post("https://admin.megatop.by/api/v1/user/login").then().
                assertThat().
                    statusCode(422).
                    body("message", equalTo("Вы ввели неверный номер телефона и/или пароль")).
                    body("errors.phone[0]", equalTo("Вы ввели неверный номер телефона и/или пароль")).
                    body("status", equalTo("error"));
    }

    @Test
    public void testPostFromWithIncompleteEmail() {
        PostObject po = new PostObject("4478588", "qwerty1Q");
        given().header("Content-Type", "application/json").body(po).
                when().post("https://admin.megatop.by/api/v1/user/login").then().
                assertThat().
                statusCode(422).
                body("message", equalTo("Вы ввели неверный номер телефона и/или пароль")).
                body("errors.phone[0]", equalTo("Вы ввели неверный номер телефона и/или пароль")).
                body("status", equalTo("error"));
    }

    @Test
    public void testPostFromWithInсorrectPassword() {
        PostObject po = new PostObject("447858875", "qwerty1");
        given().header("Content-Type", "application/json").body(po).
                when().post("https://admin.megatop.by/api/v1/user/login").then().
                assertThat().
                statusCode(422).
                body("message", equalTo("Вы ввели неверный номер телефона и/или пароль")).
                body("errors.phone[0]", equalTo("Вы ввели неверный номер телефона и/или пароль")).
                body("status", equalTo("error"));
    }

    @Test
    public void testPostFromWithCorrectField() {
        PostObject po = new PostObject("447858875", "qwertyui1Q");
        ValidatableResponse response = given().header("Content-Type", "application/json").body(po).
                when().post("https://admin.megatop.by/api/v1/user/login").then().log().all();
        Assert.assertEquals(response.extract().statusCode(), 200);
    }

    @Test
    public void testGetSearchWithParam() {
        ValidatableResponse response = given().header("Content-Type", "application/json").
                when().get("https://megatop.by/api/v1/search?query=Beagle&device=desktop").then();
        Assert.assertEquals(response.extract().statusCode(), 200);
    }

}
