package by.megatop.avchinnikova.alina.TestAPI;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SearchApi {
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
