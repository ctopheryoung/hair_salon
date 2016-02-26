import org.sql2o.*;
import java.util.List;

public class Client {
  private int id;
  private String client;
  private String phone;
  private int stylist_id;

  public Client(String client, String phone, int stylist_id) {
    this.client = client;
    this.phone = phone;
    this.stylist_id = stylist_id;
  }

  public int getId() {
    return id;
  }

  public String getClient() {
    return client;
  }

  public String getPhone() {
    return phone;
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
             this.getPhone().equals(newClient.getPhone()) &&
             this.getStylistId() == newClient.getStylistId();
    }
  }
  //CREATE
  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients(client, phone, stylist_id) VALUES (:client, :phone, :stylist_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("client", client)
        .addParameter("phone", phone)
        .addParameter("stylist_id", stylist_id)
        .executeUpdate()
        .getKey();
    }
  }

  //READ
  public static List<Client> all() {
      String sql = "SELECT id, client, phone, stylist_id FROM clients";
      try(Connection con = DB.sql2o.open()) {
        return con.createQuery(sql).executeAndFetch(Client.class);
      }
  }

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE id=:id";
      Client client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
      return client;
    }
  }

  //UPDATE
  public void update(String newClientName, String newPhone, int newStylistId) {
    this.client = newClientName;
    this.phone = newPhone;
    this.stylist_id = newStylistId;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET client = :client, phone = :phone, stylist_id = :stylist_id WHERE id = :id";
      con.createQuery(sql)
        .addParameter("client", newClientName)
        .addParameter("phone", newPhone)
        .addParameter("stylist_id", newStylistId)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  //DELETE
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

}
