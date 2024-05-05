package Entities;

import Audios.Audio;
import Loader.Load;
import MAINGAME.Panel;
import Tiles.Tile;
import Tiles.TileManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static Audios.AudioConstants.FIRE;
import static MAINGAME.Panel.TILE_SIZE;

public class EnemyManager {

    Panel panel ;
    TileManager tileManager ;
    public FireSkull ball;
    ArrayList<Trap> traps = new ArrayList<>();
    Player player;
    BufferedImage enemyMap;
    Audio audio ;
    public EnemyManager(Panel panel , TileManager tileManager, Player player)
    {
        enemyMap = Load.Image("EnemyMap.png");
        this.panel = panel;
        this.tileManager = tileManager;
        ball = new FireSkull(-100,64,20 , player , tileManager);
        this.player = player;
        audio = new Audio();
        addTraps();

    }

    private void addTraps() {
        int k = 0;
        Color color ;
        for (int i = 0 ; i < 12; i++) {
            for (int j = 0 ; j < 200; j++) {
                color = new Color(enemyMap.getRGB((j* TILE_SIZE +  (j+1)*TILE_SIZE ) / 2,(i * TILE_SIZE +  (i+1)*TILE_SIZE ) / 2) );
                if ((color.getRed() == 70 & color.getBlue() == 70 && color.getGreen() == 70)) {
                    k++;
                    traps.add(new VerticalBall(j*TILE_SIZE,i*TILE_SIZE, TILE_SIZE , TILE_SIZE , player , tileManager));
                }
                if (color.getRed() == 200 & color.getBlue() == 100 && color.getGreen() == 100) {
                    traps.add(new HorizontalBall(j*TILE_SIZE,i*TILE_SIZE, TILE_SIZE , TILE_SIZE , player , tileManager));
                }
            }
        }
    }


    public void update (int xOffset)
    {
        ball.update(xOffset);
        for (Trap trap : traps ) trap.update(xOffset);

        audio.playAction(FIRE);
    }

    public void draw (Graphics2D g , int xOffset)
    {
        ball.draw(g , xOffset);
        for (Trap trap : traps ) trap.draw(g , xOffset);
    }
}
