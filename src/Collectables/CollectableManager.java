package Collectables;

import Entities.HorizontalBall;
import Entities.Player;
import Entities.VerticalBall;
import Levels.Level;
import Loader.Load;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static MAINGAME.Panel.TILE_SIZE;

public class CollectableManager {

    public static ArrayList<Health> healths ;
    static Player player;
    static BufferedImage healthMap;
    String level ;

    public CollectableManager(Player player, String Level) {
        healths = new ArrayList<>();
        this.player = player;
        level = Level;
        addHealth(Level);
    }

    public static void addHealth(String Level) {
        healthMap = Load.Image(Level+".png");
        if(!healths.isEmpty()) healths.clear();
        Color color ;
        for (int i = 0 ; i < 12; i++) {
            for (int j = 0 ; j < 200; j++) {
                color = new Color(healthMap.getRGB((j* TILE_SIZE +  (j+1)*TILE_SIZE ) / 2,(i * TILE_SIZE +  (i+1)*TILE_SIZE ) / 2) );
                if ((color.getRed() == 200 & color.getBlue() == 200 && color.getGreen() == 200)) {
                    healths.add(new Health(j*TILE_SIZE,i*TILE_SIZE, player ));
                }
            }
        }
    }



    public void update()
    {
        if (!healths.isEmpty())
            for (int i = 0; i < healths.size(); i++) {
            healths.get(i).isHit();
            if (healths.get(i).dead)
            {
                healths.remove(i);
            }
        }
    }

    public void draw(Graphics2D g , int xOffset) {
        if (!healths.isEmpty())
            for (int i = 0; i < healths.size(); i++) {
            if (!healths.get(i).dead)
                healths.get(i).draw(g, xOffset);
        }
    }
}
