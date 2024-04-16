package MAINGAME;

public class Game implements Runnable{

    Frame frame ;
    final int FPS = 60 ;
    Thread thread ;

    //Panel panel ;
    Game ()
    {
        frame = new Frame(this);
        //this.panel = frame.panel;
        startGame();
    }

    void startGame ()
    {
        thread = new Thread();
        thread.start();
        run();
    }
    @Override
    public void run() {
        double timeTodraw =  (1_000_000_000 / FPS );
        long nextDrawTime = (long) (System.nanoTime() + timeTodraw);

        while(true) {

            frame.panel.update();
            frame.panel.repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime() ;
                remainingTime = remainingTime / 1_000_000 ;

                if (remainingTime < 0 ) remainingTime = 0;

                Thread.sleep((long)remainingTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            nextDrawTime += timeTodraw;
        }
    }
}
