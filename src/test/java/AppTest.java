import org.fluentlenium.adapter.FluentTest;
import static org.junit.Assert.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Hair Salon Manager");
  }

  @Test
  public void stylistIsDisplayedTest() {
    Stylist testStylist = new Stylist("Larry");
    testStylist.save();
    goTo("http://localhost:4567/stylists");
    assertThat(pageSource()).contains("Larry");
  }

  @Test
    public void clientIsDisplayedTest() {
    Client testClient = new Client("Donald Trump", "123-456-7890", 2);
    testClient.save();
    goTo("http://localhost:4567/client/all");
    assertThat(pageSource()).contains("Donald Trump");
  }

  @Test
  public void clientPhoneFormIsDisplayed() {
    Stylist testStylist = new Stylist("Larry");
    testStylist.save();

    Client testClient = new Client("Donald Trump", "111-111-1111", testStylist.getId());
    testClient.save();

    String clientDisplay = String.format("http://localhost:4567/clients/%d", testClient.getId());
    goTo(clientDisplay);
    assertThat(pageSource()).contains("111-111-1111");
  }

  @Test
  public void clientIsDeletedAndSuccessPageShows() {
    Stylist testStylist = new Stylist("Larry");
    testStylist.save();

    Client testClient = new Client("Donald Trump", "111-111-1111", testStylist.getId());
    testClient.save();

    String clientPage = String.format("http://localhost:4567/clients/%d", testClient.getId());
    goTo(clientPage);
    click("button", withText("Delete Client"));
    assertThat(pageSource()).contains("Client has succesfully been deleted.");
  }

  @Test
  public void allClientsDisplayAllClientNamesOnStylistage() {
    Stylist testStylist = new Stylist("Larry");
    testStylist.save();
    Client firstClient = new Client("Donald Trump", "123-456-7890", testStylist.getId());
    firstClient.save();
    Client secondClient = new Client("Hillary Clinton", "111-111-1111", testStylist.getId());
    secondClient.save();
    String categoryPath = String.format("http://localhost:4567/stylists/%d", testStylist.getId());
    goTo(categoryPath);
    assertThat(pageSource()).contains("Donald Trump");
    assertThat(pageSource()).contains("Hillary Clinton");
  }
}
