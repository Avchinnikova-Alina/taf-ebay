package by.megatop.avchinnikova.alina;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RestTest {

    @Test
    public void testGetLogin() {
        given().when().get("https://my.megatop.by/login").
                then().log().body();
    }

    @Test
    public void testPostFromWithEmptyField() {
        PostObject po = new PostObject("", "");
        given().header("Content-Type", "application/json").body(po).
                when().post("https://admin.megatop.by/api/v1/user/login").
                then().log().status().log().body();
        ValidatableResponse response = given().header("Content-Type", "application/json").body(po).
                when().post("https://admin.megatop.by/api/v1/user/login").
                then().log().status().log().body();
        Assert.assertEquals(response.extract().statusCode(), 500);
    }
    @Test
    public void testPostFromWithEmptyEmail() {
        PostObject po = new PostObject("", "qwerty1Q");
        given().header("Content-Type", "application/json").body(po).
                when().post("https://admin.megatop.by/api/v1/user/login").
                then().log().status().log().body();
        ValidatableResponse response = given().header("Content-Type", "application/json").body(po).
                when().post("https://admin.megatop.by/api/v1/user/login").
                then().log().status().log().body();
        Assert.assertEquals(response.extract().statusCode(), 500);
    }

    @Test
    public void testPostFromWithEmptyPassword() {
        PostObject po = new PostObject("447858875", "");
        given().header("Content-Type", "application/json").body(po).
                when().post("https://admin.megatop.by/api/v1/user/login").
                then().log().status().log().body();
        ValidatableResponse response = given().header("Content-Type", "application/json").body(po).
                when().post("https://admin.megatop.by/api/v1/user/login").
                then().log().status().log().body();
        String expectedResult="Вы ввели неверный номер телефона и/или пароль";
        String actualResult = "\u0412\u044B \u0432\u0432\u0435\u043B\u0438 \u043D\u0435\u0432\u0435\u0440\u043D\u044B\u0439 \u043D\u043E\u043C\u0435\u0440 \u0442\u0435\u043B\u0435\u0444\u043E\u043D\u0430 \u0438/\u0438\u043B\u0438 \u043F\u0430\u0440\u043E\u043B\u044C";
        Assert.assertEquals(response.extract().statusCode(), 422);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testPostFromWithIncorrectEmail() {
        PostObject po = new PostObject("117858875", "qwerty1Q");
        given().header("Content-Type", "application/json").body(po).
                when().post("https://admin.megatop.by/api/v1/user/login").
                then().log().status().log().body();
        ValidatableResponse response = given().header("Content-Type", "application/json").body(po).
                when().post("https://admin.megatop.by/api/v1/user/login").
                then().log().status().log().body();
        String expectedResult="Вы ввели неверный номер телефона и/или пароль";
        String actualResult = "\u0412\u044B \u0432\u0432\u0435\u043B\u0438 \u043D\u0435\u0432\u0435\u0440\u043D\u044B\u0439 \u043D\u043E\u043C\u0435\u0440 \u0442\u0435\u043B\u0435\u0444\u043E\u043D\u0430 \u0438/\u0438\u043B\u0438 \u043F\u0430\u0440\u043E\u043B\u044C";
        Assert.assertEquals(response.extract().statusCode(), 422);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testPostFromWithIncompleteEmail() {
        PostObject po = new PostObject("4478588", "qwerty1Q");
        given().header("Content-Type", "application/json").body(po).
                when().post("https://admin.megatop.by/api/v1/user/login").
                then().log().status().log().body();
        ValidatableResponse response = given().header("Content-Type", "application/json").body(po).
                when().post("https://admin.megatop.by/api/v1/user/login").
                then().log().status().log().body();
        String expectedResult="Вы ввели неверный номер телефона и/или пароль";
        String actualResult = "\u0412\u044B \u0432\u0432\u0435\u043B\u0438 \u043D\u0435\u0432\u0435\u0440\u043D\u044B\u0439 \u043D\u043E\u043C\u0435\u0440 \u0442\u0435\u043B\u0435\u0444\u043E\u043D\u0430 \u0438/\u0438\u043B\u0438 \u043F\u0430\u0440\u043E\u043B\u044C";
        Assert.assertEquals(response.extract().statusCode(), 422);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testPostFromWithInсorrectPassword() {
        PostObject po = new PostObject("447858875", "qwerty1");
        given().header("Content-Type", "application/json").body(po).
                when().post("https://admin.megatop.by/api/v1/user/login").
                then().log().status().log().body();
        ValidatableResponse response = given().header("Content-Type", "application/json").body(po).
                when().post("https://admin.megatop.by/api/v1/user/login").
                then().log().status().log().body();
        String expectedResult="Вы ввели неверный номер телефона и/или пароль";
        String actualResult = "\u0412\u044B \u0432\u0432\u0435\u043B\u0438 \u043D\u0435\u0432\u0435\u0440\u043D\u044B\u0439 \u043D\u043E\u043C\u0435\u0440 \u0442\u0435\u043B\u0435\u0444\u043E\u043D\u0430 \u0438/\u0438\u043B\u0438 \u043F\u0430\u0440\u043E\u043B\u044C";
        Assert.assertEquals(response.extract().statusCode(), 422);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testPostFromWithCorrectField() {
        PostObject po = new PostObject("447858875", "qwertyui1Q");
        given().header("Content-Type", "application/json").body(po).
                when().post("https://admin.megatop.by/api/v1/user/login").
                then().log().status().log().body();
        ValidatableResponse response = given().header("Content-Type", "application/json").body(po).
                when().post("https://admin.megatop.by/api/v1/user/login").
                then().log().status().log().body();
        Assert.assertEquals(response.extract().statusCode(), 200);
    }
}
