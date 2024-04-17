package Tiles;

import java.awt.*;

import static MAINGAME.Panel.*;
import static MAINGAME.Panel.TILE_SIZE;

public class TileHitBox {
    Rectangle[][] hitBox = new Rectangle[TILES_IN_WIDTH][TILES_IN_HEIGHT];
    Dimension box = new Dimension(TILE_SIZE,TILE_SIZE);

    public TileHitBox ()
    {
        setHitBox();
    }
    public void setHitBox() {
        for (int x = 0 ; x < hitBox.length ; x++) {
            for (int y = 0; y < hitBox[x].length; y++) {
                hitBox[x][y] = new Rectangle(box);
                hitBox[x][y].x = x * TILE_SIZE;
                hitBox[x][y].y = y * TILE_SIZE;
            }
        }
    }

    public void draw (Graphics g) {
        for (int x = 0; x < hitBox.length; x++)
            for (int y = 0; y < hitBox[x].length; y++)
            {
                g.drawRect(hitBox[x][y].x,hitBox[x][y].y,TILE_SIZE,TILE_SIZE);
            }
    }
}
