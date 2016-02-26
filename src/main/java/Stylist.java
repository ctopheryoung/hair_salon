import org.sql2o.*;
import java.util.List;

public class Stylist {
  private int id;
  private String stylist;

  public Stylist(String stylist) {
    this.stylist = stylist;
  }

  public int getId() {
    return id;
  }

  public String getStylist() {
    return stylist;
  }

  @Override
  public boolean equals(Object otherStylist){
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getStylist().equals(newStylist.getStylist()) &&
        this.getId() == newStylist.getId();
    }
  }

  //CREATE
  public void save() {
    String sql = "INSERT INTO stylists(stylist) VALUES (:stylist)";
    try (Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
        .addParameter("stylist", stylist)
        .executeUpdate()
        .getKey();
    }
  }

  //READ
  public static List<Stylist> all() {
    String sql = "SELECT id, stylist FROM stylists";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  public static Stylist find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists WHERE id=:id";
      Stylist stylist = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Stylist.class);
      return stylist;
    }
  }

  //UPDATE
  public void update(String newStylistName) {
    this.stylist = newStylistName;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stylists SET stylist = :stylist WHERE id = :id";
      con.createQuery(sql)
        .addParameter("stylist", newStylistName)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  //DELETE
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
}
