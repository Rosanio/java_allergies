import java.util.HashMap;
import java.util.ArrayList;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {}

  public static ArrayList checkAllergies(Integer allergyScore) {
    HashMap<Integer, String> allergyIndex = new HashMap<Integer, String>();
    allergyIndex.put(1, "eggs");
    allergyIndex.put(2, "peanuts");
    allergyIndex.put(4, "shellfish");
    allergyIndex.put(8, "strawberries");
    allergyIndex.put(16, "tomatoes");
    allergyIndex.put(32, "chocolate");
    allergyIndex.put(64, "pollen");
    allergyIndex.put(128, "cats");
    Integer counter = 128;
    ArrayList<String> allergies = new ArrayList<String>();
    allergies.add(allergyIndex.get(counter));
    return allergies;
  }
}
