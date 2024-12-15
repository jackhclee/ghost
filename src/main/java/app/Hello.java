package app;

import java.util.Random;

public class Hello {
  public static void main(String[] argv) {
    Random ran = new Random();
    System.out.println(ran.nextInt());
    String script = System.getenv("SCRIPTNAME");
    try {
    if (script != null) {
      // BAD: The script to be executed is controlled by the user.
      Runtime.getRuntime().exec(script);
    }
    } catch (Exception e) {
      System.out.println(e.getStackTrace());
    }
  }
}
