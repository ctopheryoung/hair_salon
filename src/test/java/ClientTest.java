import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.sql2o.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
      assertEquals(Client.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfClientsAreTheSame() {
    Client firstClient = new Client("Sven", "123-456-7890", 2);
    Client secondClient = new Client("Sven", "123-456-7890", 2);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_returnsTrueIfClientsAreTheSame() {
    Client testClient = new Client("Sven", "123-456-7890", 2);
    testClient.save();
    assertTrue(Client.all().get(0).equals(testClient));
  }

  @Test
  public void save_assignsIdToClient() {
    Client myClient = new Client("Sven", "123-456-7890", 2);
    myClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(myClient.getId(), savedClient.getId());
  }

  @Test
  public void find_findsClientInDatabase_true() {
    Client testClient = new Client("Sven", "123-456-7890", 2);
    testClient.save();
    Client savedClient = Client.find(testClient.getId());
    assertTrue(testClient.equals(savedClient));
  }

  // @Test
  // public void updateName_updatesRestaurantNameInDatabase() {
  //   Restaurant testRestaurant = new Restaurant("Lardo", "$", "Casual", 2);
  //   testRestaurant.save();
  //   testRestaurant.updateName("Pig Fat");
  //   assertEquals(Restaurant.all().get(0).getName(), "Pig Fat");
  //   // assertTrue(((Restaurant.all().get(0)).getName()).equals("Pig Fat"));
  // }
  //
  // @Test
  // public void updatePrice_updatesRestaurantPriceInDatabase() {
  //   Restaurant testRestaurant = new Restaurant("Lardo", "$", "Casual", 2);
  //   testRestaurant.save();
  //   testRestaurant.updatePrice("$$");
  //   assertEquals(Restaurant.all().get(0).getPrice(), "$$");
  // }
  //
  // @Test
  // public void updateVibe_updatesRestaurantVibeInDatabase() {
  //   Restaurant testRestaurant = new Restaurant("Lardo", "$", "Casual", 2);
  //   testRestaurant.save();
  //   testRestaurant.updateVibe("Divey");
  //   assertEquals(Restaurant.all().get(0).getVibe(), "Divey");
  // }
  //
  // @Test
  // public void updateCusineId_updatesRestaurantCuisineIdInDatabase() {
  //   Restaurant testRestaurant = new Restaurant("Lardo", "$", "Casual", 2);
  //   testRestaurant.save();
  //   testRestaurant.updateCuisineId(3);
  //   assertEquals(Restaurant.all().get(0).getCuisineId(), 3);
  // }
  //
  // @Test
  // public void deleteRestaurant_deletesRestaurantFromDatabase() {
  //   Restaurant testRestaurant = new Restaurant("Lardo", "$", "Casual", 2);
  //   testRestaurant.save();
  //   testRestaurant.deleteRestaurant();
  //   assertEquals(Restaurant.all().size(), 0);
  // }
  //
  // @Test
  // public void save_savesCuisineIdIntoRestaurant() {
  //   Cuisine myCuisine = new Cuisine("American");
  //   myCuisine.save();
  //   Restaurant myRestaurant = new Restaurant("Lardo", "$", "Casual", myCuisine.getId());
  //   myRestaurant.save();
  //   Restaurant savedRestaurant = Restaurant.find(myRestaurant.getId());
  //   assertEquals(savedRestaurant.getCuisineId(), myCuisine.getId());
  // }
  //
  // @Test
  // public void getCuisineType_getsCuisineAssociatedWithRestaurant() {
  //   Cuisine myCuisine = new Cuisine("American");
  //   myCuisine.save();
  //   Restaurant myRestaurant = new Restaurant("Lardo", "$", "Casual", myCuisine.getId());
  //   myRestaurant.save();
  //   assertEquals(myRestaurant.getCuisineType(), myCuisine.getType());
  // }
}
