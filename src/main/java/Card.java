public class Card{
  private String[] suits = {"Spades","Clubs","Diamonds","Hearts"};
  private String[] values = {"Ace","1","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};

  public String[] getSuit(){
    return suits;
  }
  
  public String[] getValues(){
    return values;
  }
}
