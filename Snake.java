import java.util.*;
public class Snake{
    private ArrayList<snakeBody> s;
    private String direction;
    private boolean isAlive;
    private int[] foodCords;
    public Snake(){
        s = new ArrayList<snakeBody>();
        s.add(new snakeBody(10,10));
        s.add(new snakeBody(9,10));
        s.get(0).setConnectedTo(s.get(1));
        s.add(new snakeBody(8,10));
        s.get(1).setConnectedTo(s.get(2));
        direction = "east";
        isAlive = true; 
        foodCords = new int[2];
        setRandFoodCords();
    }
    public int[] getFoodCords(){
        return foodCords;
    }
    private boolean contains(int x, int y){
        for(snakeBody sb: s){
            if(sb.getX() == x && sb.getY() == y){
                return true;
            }
        }   
        return false;
    }
    public void setRandFoodCords(){
        int X = (int)(Math.random()*38)+1;
        int Y = (int)(Math.random()*38)+1;
        while(contains(X,Y)){
            X = (int)(Math.random()*38)+1;
            Y = (int)(Math.random()*38)+1;
        }
        foodCords = new int[]{X,Y};
    }   
    public ArrayList<snakeBody> getSnake(){
        return s;
    }
    public void grow(){
        int x = s.get(s.size()-1).getX();
        int y = s.get(s.size()-1).getY();
        moveSnake();
        s.add(new snakeBody(x,y));
        s.get(s.size()-2).setConnectedTo(s.get(s.size()-1));
        setRandFoodCords();
    }
    public void checkCollide(){
        if(s.get(0).getX() == 0 || s.get(0).getX() == 49 || s.get(0).getY() == 0 || s.get(0).getY() == 49){
            isAlive = false;
        }
        for(int i = 1; i < s.size(); i++){
            if(s.get(i).getX() == s.get(0).getX() && s.get(i).getY() == s.get(0).getY()){
                isAlive = false;
            }
        }
        if(isAlive && foodCollide()){
            grow();
        }
        else if(isAlive){
            moveSnake();
        }
    }
    public boolean foodCollide(){
        if(s.get(0).getX() == foodCords[0] && s.get(0).getY() == foodCords[1]){
            return true;
        }
        return false;
    }
    public boolean getIsAlive(){
        return isAlive;
    }
    public void setDirection(String d){
        if(d.equals("north") && !direction.equals("south")){
            direction = d;
        }
        else if(d.equals("south") && !direction.equals("north")){
            direction = d;
        }
        else if(d.equals("east") && !direction.equals("west")){
            direction = d;
        }
        else if(d.equals("west") && !direction.equals("east")){
            direction = d;
        }
    }
    private void moveSnake(){
        if(direction.equals("west")){
            s.get(0).move(s.get(0).getX()-1,s.get(0).getY()); 
        }
        else if(direction.equals("east")){
            s.get(0).move(s.get(0).getX()+1,s.get(0).getY()); 
        }
        else if(direction.equals("north")){
            s.get(0).move(s.get(0).getX(),s.get(0).getY()-1); 
        }
        else{
            s.get(0).move(s.get(0).getX(),s.get(0).getY()+1); 
        }
    }
}