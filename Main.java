import javax.swing.*;

public class Main{
    public static void main(String[] args){
        JFrame frame = new JFrame("Snake");
        Snake snake = new Snake();
        Display screen = new Display(snake);
        frame.add(screen);
        KeyboardThread keyboard = new KeyboardThread(snake);
        frame.addKeyListener(keyboard);
        frame.setSize(1020,1040);
        frame.setVisible(true);
        while(snake.getIsAlive()){
            try{
                Thread.sleep(200);
            }
            catch(Exception e){
                System.out.println(e);
            }
            snake.checkCollide();
            screen.draw();
        }
    }
}