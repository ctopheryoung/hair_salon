import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
      assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfStylistsAreTheSame() {
    Stylist stylistOne = new Stylist("Bob");
    Stylist stylistTwo = new Stylist("Bob");
    assertTrue(stylistOne.equals(stylistTwo));
  }

  // @Test
  // public void save_returnsTrueIfCuisinesAreTheSame() {
  //   Cuisine testCuisine = new Cuisine("American");
  //   testCuisine.save();
  //   assertTrue(Cuisine.all().get(0).equals(testCuisine));
  // }
  //
  // @Test
  // public void save_assignsIdToObject() {
  //   Cuisine myCuisine = new Cuisine("American");
  //   myCuisine.save();
  //   Cuisine savedCuisine = Cuisine.all().get(0);
  //   assertEquals(myCuisine.getId(), savedCuisine.getId());
  // }
  //
  // @Test
  // public void update_updatesCuisineTypeInDB() {
  //   Cuisine myCuisine = new Cuisine("American");
  //   myCuisine.save();
  //   myCuisine.update("Italian");
  //   assertEquals(Cuisine.all().get(0).getType(), "Italian");
  // }
  //
  // @Test
  // public void delete_deletesCuisineTypeFromDB() {
  //   Cuisine myCuisine = new Cuisine("American");
  //   myCuisine.save();
  //   myCuisine.delete();
  //   assertEquals(Cuisine.all().size(), 0);
  // }
}
