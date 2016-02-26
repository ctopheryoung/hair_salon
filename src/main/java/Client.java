import org.sql2o.*;
import java.util.List;

public class Client {
  private int id;
  private String client;
  private int stylist_id;

  public Client(String client, int stylist_id) {
    this.client = client;
    this.stylist_id = stylist_id;
  }

  public int getId() {
    return id;
  }

  public String getClient() {
    return client;
  }

  public int getStylistId() {
    return stylist_id;
  }

  @Override
  public boolean equals(Object otherClient){
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getClient().equals(newClient.getClient()) &&
        this.getStylistId() == newClient.getStylistId();
    }
  }
  //CREATE
  //READ
  public static List<Client> all() {
      String sql = "SELECT id, client, stylist_id FROM clients";
      try(Connection con = DB.sql2o.open()) {
        return con.createQuery(sql).executeAndFetch(Client.class);
      }
  }

  //UPDATE
  //DELETE

}
