import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App{
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";
    Game theGame = new Game();
//Home page
    get("/", (request,response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template","templates/index.vtl");
      return new ModelAndView(model,layout);
    }, new VelocityTemplateEngine());

    post("/",(request,response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      String name1 = request.queryParams("player1");
      String name2 = request.queryParams("player2");
      theGame.addPlayer(name1);
      theGame.addPlayer(name2);
      theGame.createDeck();
      // theGame.shuffle();
      theGame.deal();
      model.put("playerOne", theGame.getPlayer(1));
      model.put("playerTwo", theGame.getPlayer(2));
      model.put("turnPlayer",theGame.getPlayer(1));
      model.put("template","templates/game.vtl");
      return new ModelAndView(model,layout);
    },new VelocityTemplateEngine());

    //Score page


    get("/game", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template","templates/game.vtl");
      model.put("playerOne", theGame.getPlayer(1));
      model.put("playerTwo", theGame.getPlayer(2));
      return new ModelAndView(model,layout);
    }, new VelocityTemplateEngine());

    //Player Specific Page

    post("/:id", (request,response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      Player aPlayer = theGame.getPlayer(Integer.parseInt(request.params(":id")));
      // Player aPlayer = theGame.getPlayer(theGame.getCurrentPlayer());
      String opponent = request.queryParams("player");
      String card = request.queryParams("card");
      // if(!theGame.turn(opponent, card) && !theGame.gameOver()){
      theGame.turn(opponent, card);
        theGame.changeTurn();
      // }
      model.put("playerOne", theGame.getPlayer(1));
      model.put("playerTwo", theGame.getPlayer(2));
      model.put("turnPlayer",theGame.getPlayer(theGame.getCurrentPlayer()));
      model.put("template","templates/game.vtl");
      return new ModelAndView(model,layout);
    },new VelocityTemplateEngine());

    get("/:id",(request,response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      Player aPlayer = theGame.getPlayer(Integer.parseInt(request.params(":id")));
      model.put("player",aPlayer);
      model.put("hand",aPlayer.getHand());
      model.put("template", "templates/playerturn.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
