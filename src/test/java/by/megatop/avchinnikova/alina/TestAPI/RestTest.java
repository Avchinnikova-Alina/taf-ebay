package by.megatop.avchinnikova.alina.TestAPI;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pageAPI.ApiPage;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestTest {

    @Ignore
    @Test
    public void testGetLogin() {
        given().when().get("https://my.megatop.by/login").then();
        Assert.fail("Ничего не проверяет");
    }

    @Test
    public void testPostFromWithEmptyField() {
        ApiPage apiPage = new ApiPage("", "");
        ValidatableResponse response = given().header("Content-Type", "application/json").body(apiPage).
                when().post("https://admin.megatop.by/api/v1/user/login").then();
        Assert.assertEquals(response.extract().statusCode(), 500);
    }

    @Test
    public void testPostFromWithEmptyEmail() {
        ApiPage apiPage = new ApiPage("", "qwerty1Q");
        ValidatableResponse response = given().header("Content-Type", "application/json").body(apiPage).
                when().post("https://admin.megatop.by/api/v1/user/login").then();
        Assert.assertEquals(response.extract().statusCode(), 500);
    }

    @Test
    public void testPostFromWithEmptyPassword() {
        ApiPage apiPage = new ApiPage("447858875", "");
        given().header("Content-Type", "application/json").body(apiPage).
                when().post("https://admin.megatop.by/api/v1/user/login").then().
                assertThat().
                statusCode(422).
                body("message", equalTo("Вы ввели неверный номер телефона и/или пароль")).
                body("errors.phone[0]", equalTo("Вы ввели неверный номер телефона и/или пароль")).
                body("status", equalTo("error"));
    }

    @Test
    public void testPostFromWithIncorrectEmail() {
        ApiPage apiPage = new ApiPage("117858875", "qwerty1Q");
        given().header("Content-Type", "application/json").body(apiPage).
                when().post("https://admin.megatop.by/api/v1/user/login").then().
                assertThat().
                statusCode(422).
                body("message", equalTo("Вы ввели неверный номер телефона и/или пароль")).
                body("errors.phone[0]", equalTo("Вы ввели неверный номер телефона и/или пароль")).
                body("status", equalTo("error"));
    }

    @Test
    public void testPostFromWithIncompleteEmail() {
        ApiPage apiPage = new ApiPage("4478588", "qwerty1Q");
        given().header("Content-Type", "application/json").body(apiPage).
                when().post("https://admin.megatop.by/api/v1/user/login").then().
                assertThat().
                statusCode(422).
                body("message", equalTo("Вы ввели неверный номер телефона и/или пароль")).
                body("errors.phone[0]", equalTo("Вы ввели неверный номер телефона и/или пароль")).
                body("status", equalTo("error"));
    }

    @Test
    public void testPostFromWithInсorrectPassword() {
        ApiPage apiPage = new ApiPage("447858875", "qwerty1");
        given().header("Content-Type", "application/json").body(apiPage).
                when().post("https://admin.megatop.by/api/v1/user/login").then().
                assertThat().
                statusCode(422).
                body("message", equalTo("Вы ввели неверный номер телефона и/или пароль")).
                body("errors.phone[0]", equalTo("Вы ввели неверный номер телефона и/или пароль")).
                body("status", equalTo("error"));
    }

    @Test
    public void testPostFromWithCorrectField() {
        ApiPage apiPage = new ApiPage("447858875", "qwertyui1Q");
        ValidatableResponse response = given().header("Content-Type", "application/json").body(apiPage).
                when().post("https://admin.megatop.by/api/v1/user/login").then().log().all();
        Assert.assertEquals(response.extract().statusCode(), 200);
    }

    @Test
    public void testGetSearchWithoutQuery() {
        given().header("Content-Type", "application/json").
                when().get("https://megatop.by/api/v1/search").
                then().
                statusCode(200).
                body("products[0].name", equalTo("Полуботинки Shuzzi 05222068"));

    }

    @Test
    public void testGetSearchNameCode() {
        given().header("Content-Type", "application/json").
                when().get("https://megatop.by/api/v1/search?query=1000000093&device=desktop").
                then().statusCode(200).
                body("products[0].name", equalTo("Кроссовки Shuzzi 1000000093"));
    }

    @Test
    public void testGetSearchName() {
        given().header("Content-Type", "application/json").
                when().get("https://megatop.by/api/v1/search?query=Кроссовки Shuzzi&device=desktop").
                then().log().body().statusCode(200).
                body("products[0].name", equalTo("Сандалеты Shuzzi 05303654")).
                body("products[1].name", equalTo("Кроссовки Shuzzi 05600022")).
                body("products[2].name", equalTo("Кроссовки Shuzzi 056003"));
    }

    @Test
    public void testGetSearchWithThings() {
        given().header("Content-Type", "application/json").
                when().get("https://megatop.by/api/v1/search?query=shuzzi+and+enjoin&device=desktop").
                then().
                statusCode(200).
                body("products[0].name", equalTo("Сандалеты Shuzzi 05303654")).
                body("products[1].name", equalTo("Полуботинки Enjoin' 054510680"));

    }

}
