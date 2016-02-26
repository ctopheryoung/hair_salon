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

  @Test
  public void save_returnsTrueIfStylistIsSavedInDB() {
    Stylist testStylist = new Stylist("Fred");
    testStylist.save();
    assertTrue(Stylist.all().get(0).equals(testStylist));
  }

  @Test
  public void save_assignsIdToObject() {
    Stylist testStylist = new Stylist("Fred");
    testStylist.save();
    Stylist savedStylist = Stylist.all().get(0);
    assertEquals(testStylist.getId(), savedStylist.getId());
  }

  @Test
  public void find_findsStylistInDatabase_true() {
    Stylist testStylist = new Stylist("George");
    testStylist.save();
    Stylist savedStylist = Stylist.find(testStylist.getId());
    assertTrue(testStylist.equals(savedStylist));
  }

  @Test
  public void update__updatesStylistNameInDatabase() {
    Stylist testStylist = new Stylist("Sven");
    testStylist.save();
    testStylist.update("Bjorn");
    assertEquals(Stylist.all().get(0).getStylist(), "Bjorn");
  }

  @Test
  public void delete_deletesStylistFromDB() {
    Stylist testStylist = new Stylist("Amond Bundy");
    testStylist.save();
    testStylist.delete();
    assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void getClients_getsAllClientsAssociatedWithStylist() {
    Stylist testStylist = new Stylist("Velma");
    testStylist.save();
    Client testClientOne = new Client("Sven", "123-456-7890", testStylist.getId());
    testClientOne.save();
    Client testClientTwo = new Client("Ur", "999-999-9999", testStylist.getId());
    testClientTwo.save();
    Client testClientThree = new Client("Phelps", "111-111-1111", 999);
    testClientThree.save();
    assertEquals(testStylist.getClients().size(), 2);
  }
}
