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
    Client firstClient = new Client("Sven", 2);
    Client secondClient = new Client("Sven", 2);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_returnsTrueIfClientsAreTheSame() {
    Client testClient = new Client("Sven", 2);
    testClient.save();
    assertTrue(Client.all().get(0).equals(testClient));
  }

  @Test
  public void save_assignsIdToClient() {
    Client myClient = new Client("Sven", 2);
    myClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(myClient.getId(), savedClient.getId());
  }

}
