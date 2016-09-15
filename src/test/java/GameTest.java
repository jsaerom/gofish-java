import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;

public class GameTest{

  @Test
  public void createDeck_Adds52Cards_52(){
    Game gameTest = new Game();
    gameTest.createDeck();
    assertEquals(52,gameTest.getDeck().size());
  }

  @Test
  public void addPlayer_AddsTwoPlayers_2(){
    Game gameTest = new Game();
    gameTest.addPlayer("Andrew");
    gameTest.addPlayer("Joanna");
    assertEquals(2,gameTest.getPlayers().size());
  }


  @Test
  public void deal_Deals5CardsAndRemovesFromDeck_int(){
    Game gameTest = new Game();
    gameTest.addPlayer("Andrew");
    gameTest.addPlayer("Joanna");
    gameTest.createDeck();
    gameTest.deal();
    assertEquals(5,gameTest.getPlayer(1).getHand().size());
    assertEquals(42,gameTest.getDeck().size());
  }

  @Test
    public void goFish_DrawOneMoreCard_6(){
      Game gameTest = new Game();
      gameTest.addPlayer("Andrew");
      gameTest.addPlayer("Joanna");
      gameTest.createDeck();
      gameTest.deal();
      gameTest.goFish();
      assertEquals(6,gameTest.getPlayer(1).getHand().size());
      assertEquals(5,gameTest.getPlayer(2).getHand().size());
  }

  @Test
  public void changeTurn_SwitchesFrom3rdPlayerTo1st_1(){
    Game gameTest = new Game();
    gameTest.addPlayer("Andrew");
    gameTest.addPlayer("Joanna");
    gameTest.addPlayer("Summer");
    gameTest.setCurrentPlayer(3);
    gameTest.changeTurn();
    assertEquals(1,gameTest.getCurrentPlayer());
  }
  // @Test
  // public void turn_FindsPlayer_true(){
  //   Game gameTest = new Game();
  //   gameTest.addPlayer("Andrew");
  //   gameTest.addPlayer("Joanna");
  //   gameTest.addPlayer("Summer");
  //   assertEquals(false,gameTest.turn("Andrsdfew","Ace of Spades"));
  // }

  // @Test
  // public void turn_ChecksToSee_1(){
  //   Game gameTest = new Game();
  //   gameTest.addPlayer("Andrew");
  //   gameTest.addPlayer("Joanna");
  //   gameTest.addPlayer("Summer");
  //   gameTest.setCurrentPlayer(3);
  //   gameTest.changeTurn();
  //   assertEquals(1,gameTest.getCurrentPlayer());
  // }

  @Test
  public void handOverCard_GivesCardToOtherPlayer_6(){
    Game gameTest = new Game();
    gameTest.addPlayer("Andrew");
    gameTest.addPlayer("Joanna");
    gameTest.createDeck();
    gameTest.deal();
    gameTest.setCurrentPlayer(1);
    gameTest.handOverCard("2",0);
    assertEquals(6,gameTest.getPlayer(2).getHand().size());
  }

  @Test
  public void turn_PlayerFoundCardFound_true(){
    Game gameTest = new Game();
    gameTest.addPlayer("Andrew");
    gameTest.addPlayer("Joanna");
    gameTest.createDeck();
    gameTest.deal();
    gameTest.setCurrentPlayer(1);
    assertEquals(true,gameTest.turn("Andrew","2"));
    assertEquals(6,gameTest.getPlayer(2).getHand().size());
  }

  @Test
  public void removePairs_PairsRemoved_2(){
    Game gameTest = new Game();
    gameTest.addPlayer("Andrew");
    gameTest.addPlayer("Joanna");
    gameTest.getPlayer(1).setHand("-");
    gameTest.getPlayer(1).setHand("2");
    gameTest.getPlayer(1).setHand("-");
    gameTest.getPlayer(1).setHand("3");
    gameTest.getPlayer(1).setHand("-");
    gameTest.getPlayer(1).setHand("-");
    gameTest.removePairs();
    assertEquals(2,gameTest.getPlayer(1).getHand().size());
  }

  @Test
  public void checkForPairs_ReplacePairsWithDashes_ArrayList(){
    Game gameTest = new Game();
    gameTest.addPlayer("Andrew");
    gameTest.addPlayer("Joanna");
    gameTest.getPlayer(1).setHand("1");
    gameTest.getPlayer(1).setHand("2");
    gameTest.getPlayer(1).setHand("4");
    gameTest.getPlayer(1).setHand("3");
    gameTest.getPlayer(1).setHand("1");
    gameTest.getPlayer(1).setHand("2");
    gameTest.checkForPairs();
    ArrayList<String> expectedOutput = new ArrayList<String>();
    expectedOutput.add("-");
    expectedOutput.add("-");
    expectedOutput.add("4");
    expectedOutput.add("3");
    expectedOutput.add("-");
    expectedOutput.add("-");
    assertEquals(expectedOutput,gameTest.getPlayer(1).getHand());
  }

}
