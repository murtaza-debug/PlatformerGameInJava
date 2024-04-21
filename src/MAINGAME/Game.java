package MAINGAME;
import static GameStates.StateConstants.*;
public class Game implements Runnable{

    Frame frame ;
    final int FPS = 60 ;
    final in UPS = 80 ;
    Thread thread ;

    //Panel panel ;
    Game ()
    {
        frame = new Frame(this);
        startGame();
    }

    void startGame ()
    {
        thread = new Thread(this);
        thread.start();
    }
    @Override
    public void run() {
        double timeTodraw =  (1_000_000_000 / FPS );
        double timeToupdate = (1_000_000_000 / UPS) ;

        long currentTime = System.nanoTime() ;
        long previousTime = System.nanoTime () ;

        while(true) {
                currentTime = System.nanoTime () ;

                frame.panel.update();
                if (currentTime - previousTime >= timeTodraw) 
                {
                frame.panel.repaint();
                previousTime = currentTime; 
                }

            /*try {
                double remainingTime = nextDrawTime - System.nanoTime() ;
                remainingTime = remainingTime / 1_000_000 ;

                if (remainingTime < 0 ) remainingTime = 0;

                Thread.sleep((long)remainingTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            nextDrawTime += timeTodraw; */
        }
    }
}
