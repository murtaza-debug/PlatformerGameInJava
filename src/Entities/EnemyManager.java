package Entities;

import MAINGAME.Panel;
import Tiles.TileManager;

import java.awt.*;

import static MAINGAME.Panel.TILE_SIZE;

public class EnemyManager {

    Panel panel ;
    DarkKnight darkKnight ;
    public EnemyManager(Panel panel , TileManager tileManager , int xOffset)
    {
        this.panel = panel;
        darkKnight = new DarkKnight(3 * TILE_SIZE,9 *TILE_SIZE,64,64 , tileManager ,xOffset);
    }


    public void update (int xOffset)
    {
        darkKnight.update(xOffset);

    }

    public void draw (Graphics2D g , int xOffset)
    {
        darkKnight.draw(g,xOffset);
    }
}
