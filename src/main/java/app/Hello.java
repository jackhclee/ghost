package app;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.sql.DriverManager;
import java.util.Random;

public class Hello {
  public static void main(String[] argv) {
    Random ran = new Random();
    System.out.println(ran.nextInt());
    String script = System.getenv("SCRIPTNAME");
    int notused = 1234;
    try {
    if (script != null) {
      // BAD: The script to be executed is controlled by the user.
      Runtime.getRuntime().exec(script);
    }
    } catch (Exception e) {
      System.out.println(e.getStackTrace());
      System.out.println(e.getStackTrace());
    }
    try {

      var client = HttpClient.newHttpClient();
      var request = HttpRequest.newBuilder()
          .uri(URI.create("http://httpbin.org/hidden-basic-auth/t/t"))
          .header("Accept", "application/json")
          .header("Authorization", "Basic dDp0")
          .build();
      HttpResponse<String> response =
          client.send(request, BodyHandlers.ofString());
      System.out.println(response.statusCode());
      System.out.println(response.body());

      Class.forName("org.hsqldb.jdbc.JDBCDriver");
      var conn = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "SA", "");

      try (var stat = conn.createStatement()) {
        var rs = stat.executeQuery("select (1+1) FROM   INFORMATION_SCHEMA.TABLES");// + new Random(12345).nextInt() + " + " + argv[0]);
        while (rs.next()) {
          System.out.println(rs.getInt(1));
        }
        System.out.println("""
            $$$$$$
            ******
            """);
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static void notUsed() {
    System.out.println("Never Used");
  }
}
