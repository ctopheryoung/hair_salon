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
}
