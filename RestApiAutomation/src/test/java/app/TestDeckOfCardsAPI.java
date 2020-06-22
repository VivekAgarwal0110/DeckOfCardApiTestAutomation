package app;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.ReUsableMethods;

public class TestDeckOfCardsAPI {

  String deck_id;
  String response_str;

  @BeforeClass
  public void setEnv() {
    RestAssured.baseURI = "https://deckofcardsapi.com";
  }

  @org.testng.annotations.Test
  public void When_CreateNewDeckOFCard_Expect_SuccessTrue() {

    response_str = given().log().all()//.queryParam("deck_count", 1)
        .when().get("api/deck/new/")
        .then().assertThat().log().all().statusCode(200).extract().response().asString();
    String success_msg = ReUsableMethods.rawToJson(response_str).getString("success");
    Assert.assertEquals(success_msg, "true");

  }

  @AfterMethod
  public void setDeckId() {
    deck_id = ReUsableMethods.rawToJson(response_str).getString("deck_id");
    System.out.println("deck_id : " + deck_id);
  }

  @org.testng.annotations.Test
  public void When_CreateNewDeckOFCardWithJokers_Expect_SuccessTrue() {

    response_str = given().log().all()
        .header("jokers_enabled", "true")
        .when().get("api/deck/new/")
        .then().assertThat().log().all().statusCode(200).extract().response().asString();
    String success_msg = ReUsableMethods.rawToJson(response_str).getString("success");
    Assert.assertEquals(success_msg, "true");

  }

  @org.testng.annotations.Test
  public void When_DrawOneFromNewDeckOFCard_Expect_SuccessTrue() {

    response_str = given().log().all()
        .header("jokers_enabled", "true")
        .when().get("api/deck/new/draw/")
        .then().assertThat().log().all().statusCode(200).extract().response().asString();
    String success_msg = ReUsableMethods.rawToJson(response_str).getString("success");
    Assert.assertEquals(success_msg, "true");

  }

  @org.testng.annotations.Test
  public void When_DrawOneFromExistingDeckOFCard_Expect_SuccessTrue() {

    String uri = "api/deck/" + deck_id + "/draw/";
    System.out.println("Using URI : " + uri);
    response_str = given().log().all()
        .when().get(uri)
        .then().assertThat().log().all().statusCode(200).extract().response().asString();
    String success_msg = ReUsableMethods.rawToJson(response_str).getString("success");
    Assert.assertEquals(success_msg, "true");

  }


}