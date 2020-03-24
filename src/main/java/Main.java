import static spark.Spark.*;
import java.io.File;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
  public static void main(String[] args) {
    
    
    get("/", (req, res) -> "Hello " + getAusgabe());
    get("/hello", (req, res) -> "<b>Ohohoh</b>!");
  }
  
  private static String getName() {
    File datei = new File("blubb.txt");
		if (datei.exists()) {
			return "ist da";
      } else {
			return "ist net da";
		}
  }
  
  private static String getAusgabe() {
    String ausgabe = "";
    	try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:assets/test.db");
			Statement stat = conn.createStatement();
			String query = "select * from personen";
			ResultSet rs = stat.executeQuery("select * from personen;");
			if (rs.next())
				ausgabe += rs.getString("name");
		    while (rs.next()) {
		    	ausgabe += " und "+ rs.getString("name");
		    }
		    rs.close();
		    conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    return ausgabe;
  }
}