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

  //UPDATE TESTS
  @Test
  public void update__updatesClientNameInDatabase() {
    Client testClient = new Client("Sven", "123-456-7890", 2);
    testClient.save();
    testClient.update("Bjorn", null, testClient.getStylistId());
    assertEquals(Client.all().get(0).getClient(), "Bjorn");
  }

  @Test
  public void update__updatesClientPhoneInDatabase() {
    Client testClient = new Client("Sven", "123-456-7890", 2);
    testClient.save();
    testClient.update(null, "123-123-1234", testClient.getStylistId());
    assertEquals(Client.all().get(0).getPhone(), "123-123-1234");
  }

  @Test
  public void update__updatesClientStylistIdInDatabase() {
    Client testClient = new Client("Sven", "123-456-7890", 2);
    testClient.save();
    testClient.update(null, null, 5);
    assertEquals(Client.all().get(0).getStylistId(), 5);
  }

  @Test
  public void deleteClient_deletesClientFromDatabase() {
    Client testClient = new Client("Sven", "123-456-7890", 1);
    testClient.save();
    testClient.delete();
    assertEquals(Client.all().size(), 0);
  }

  @Test
  public void save_savesStylistIdIntoClient() {
    Stylist testStylist = new Stylist("Louie");
    testStylist.save();
    Client testClient = new Client("Sven", "123-456-7890", testStylist.getId());
    testClient.save();
    Client savedClient = Client.find(testClient.getId());
    assertEquals(savedClient.getStylistId(), testStylist.getId());
  }

  @Test
  public void getStylistById_getsStylistNameAssociatedWithClient() {
    Stylist testStylist = new Stylist("Velma");
    testStylist.save();
    Client testClient = new Client("Sven", "123-456-7890", testStylist.getId());
    testClient.save();
    assertEquals(testClient.getStylistById(), testStylist.getStylist());
  }
}
