import org.sql2o.*;
import java.util.List;

public class Client {
  private int id;
  private String client;
  private int stylistId;

  public Client(String client, int stylistId) {
    this.client = client;
    this.stylistId = stylistId;
  }

  public int getId() {
    return id;
  }

  public String getClient() {
    return client;
  }

  public int getStylistId() {
    return stylistId;
  }

}
