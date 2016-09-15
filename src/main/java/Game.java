import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Game{
  private List<String> deck = new ArrayList<String>();
  private List<Player> players = new ArrayList<Player>();
  private int mCurrentPlayer;

  public Game(){
    mCurrentPlayer = 1;

  }

  public void createDeck(){
     String[] values = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
     for(int i = 0; i<4;i++){
       for(String value : values){
         deck.add(value);
       }
     }
     Collections.shuffle(deck);
  }

  // public void shuffle(){
  // }

  public void deal(){
    for(Player player : players){
      for(int i =0;i<5;i++){
        player.setHand(deck.get(0));
        deck.remove(0);
      }

    }
  }

  public boolean turn(String name, String card){

    int i = 0;
      while(i<players.size()){
        if(i==mCurrentPlayer){
          i++;
        }
        else if(players.get(i).getName().equals(name)){
          if(players.get(i).getHand().contains(card)){
            handOverCard(card, i);
            checkForPairs();
            return true;
          }else{
            goFish();
            int lastIndex = players.get(mCurrentPlayer).getHand().size();
            if(players.get(mCurrentPlayer).getHand().get(lastIndex-1).equals(card)){
              checkForPairs();
              return true;
            }else{
              checkForPairs();
              return false;
            }
          }
        }
      } i++;
      return false;
  }
  public boolean gameOver(){
    for(int i = 0; i<players.size();i++){
      if(players.get(i).getHand().isEmpty()){
        return true;
      }
    }
    return false;
  }

  public void handOverCard(String card,int i){
    int cardPosition = players.get(i).getHand().indexOf(card);
    players.get(mCurrentPlayer).setHand(card);
    players.get(i).getHand().remove(cardPosition);
  }

  public void goFish(){
    players.get(mCurrentPlayer).setHand(deck.get(0));
    deck.remove(0);
  }

  public void changeTurn(){
    if(mCurrentPlayer < players.size()){
      mCurrentPlayer++;
    }else{
      mCurrentPlayer = 1;
    }
  }

  public void checkForPairs(){
    int handSize = players.get(mCurrentPlayer-1).getHand().size();
    for(int i = 0; i < handSize-1; i++){
      for(int j = i+1; j<handSize;j++){
        String currentCard = players.get(mCurrentPlayer-1).getHand().get(i);
        String nextCard = players.get(mCurrentPlayer-1).getHand().get(j);
        if(currentCard.equals("-")){
          j = handSize;
        }else if(currentCard.equals(nextCard)){
          players.get(mCurrentPlayer-1).setAmountOfPairs();
          players.get(mCurrentPlayer-1).getHand().set(i,"-");
          players.get(mCurrentPlayer-1).getHand().set(j,"-");
          j = handSize;
        }
      }
    }
    removePairs();
  }

  public void removePairs(){
    int handSize = players.get(mCurrentPlayer-1).getHand().size();
    for(int i = 0; i< handSize;i++){
      String currentCard = players.get(mCurrentPlayer-1).getHand().get(i);
      if(currentCard.equals("-")){
        players.get(mCurrentPlayer-1).getHand().remove(i);
        handSize--;
        i--;
      }
    }
  }

  public int getCurrentPlayer(){
    return mCurrentPlayer;
  }

  public void setCurrentPlayer(int set){
     mCurrentPlayer = set;
  }

  public void addPlayer(String name){
    Player aPlayer = new Player(name);
    players.add(aPlayer);
    aPlayer.setId(players.size());
  }

  public List<String> getDeck(){
    return deck;
  }

  public List<Player> getPlayers(){
    return players;
  }

  public Player getPlayer(int id){
    return players.get(id-1);
  }
}
