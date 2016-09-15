import java.util.List;
import java.util.ArrayList;


public class Player{
  private String mName;
  private List<String> mHand;
  private int mId;
  private int mAmountOfPairs;

  public Player(String name){
    mName = name;
    mHand = new ArrayList<String>();
    mAmountOfPairs = 0;
  }

  public String getName(){
    return mName;
  }

  public void setHand(String card){
    mHand.add(card);
  }

  public List<String> getHand(){
    return mHand;
  }

  public void setId(int id){
    mId= id;
  }
  public void setAmountOfPairs(){
      mAmountOfPairs++;
  }

  public int getId(){
    return mId;
  }

  public int getAmountOfPairs(){
    return mAmountOfPairs;
  }
}
