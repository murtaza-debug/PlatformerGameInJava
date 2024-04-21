package MAINGAME;
import static GameStates.StateConstants.*;
public class Game implements Runnable{

    Frame frame ;
    final int FPS = 60 ;
    final int UPS = 60 ;
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
        double timeToDraw =  (1_000_000_000 / FPS );
        double timeToUpdate = (1_000_000_000 / UPS) ;

        long previousTime = System.nanoTime () ;
        double deltaU = 0 ;
        double deltaD = 0 ;

        while(true) {
            long currentTime = System.nanoTime() ;
            deltaU +=  (currentTime - previousTime) / timeToUpdate ;
            deltaD +=  (currentTime - previousTime) / timeToDraw ;

            previousTime = currentTime;

            if (deltaU >= 1 ) {
                frame.panel.update();
                deltaU--;
            }
            if (deltaD >= 1)
            {
                frame.panel.repaint();
                deltaD -- ;
            }

        }

    }
}
