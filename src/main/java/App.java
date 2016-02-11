import java.util.HashMap;
import java.util.ArrayList;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;


import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";
    String form = "templates/allergy-score.vtl";
    get("/", (request, response) -> {
      HashMap model = new HashMap();
      model.put("template", form);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/list", (request, response) -> {
      HashMap model = new HashMap();
      String scoreString = request.queryParams("score");

      if (scoreString.length() == 0){
        scoreString = "0";
      }

      Integer score = Integer.parseInt(scoreString);
      ArrayList<String> allergies = checkAllergies(score);

      model.put("allergies", allergies);
      model.put("template", "templates/allergy-list.vtl");
      model.put("form", form);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }

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
    if (allergyScore > 255 || allergyScore < 0) {
      allergies.add("You are a liar! Enter a score between 0 and 255.");
      return allergies;
    }
    else if (allergyScore == 0) {
      allergies.add("You are not allergic to anything");
      return allergies;
    }
    else {
      while(allergyScore > 0) {
        if(allergyScore >= counter) {
          allergies.add(allergyIndex.get(counter));
          allergyScore -= counter;
        }
        counter = counter / 2;
      }
      return allergies;
    }
  }
}
