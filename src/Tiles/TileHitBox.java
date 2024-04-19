package Tiles;

import Entities.Player;

import java.awt.*;

import static MAINGAME.Panel.*;
import static MAINGAME.Panel.TILE_SIZE;
import static Collision.Collision.*;
import static Tiles.TileConstants.*;

public class TileHitBox {
    public Rectangle[][] hitBox ;
    Dimension box = new Dimension(TILE_SIZE,TILE_SIZE);
    Player player;
    int[][] map1 ;
    public TileHitBox (Player player , int [][] map1)
    {
        this.map1 = map1;
        this.player = player;
        hitBox = new Rectangle[map1.length][map1[0].length ];
        setHitBox();
    }
    public void setHitBox() {

        for (int x = 0 ; x < 12 ; x++)
            for (int y = 0 ; y < 20 ; y++)
            {
                hitBox[x][y] = new Rectangle(box);
                hitBox[x][y].y = x *TILE_SIZE ;
                hitBox[x][y].x = y *TILE_SIZE ;
            }
    }

    public void updateTileHitBox ()
    {
        for (int x = 0 ; x < 12 ; x++)
            for (int y = 0 ; y < 20 ; y++) {
                if (!CanMoveHere(map1, player, x, y))
                    if (IsColliding(player, hitBox[x][y])) player.isMoving = false ;
            }
    }
    public void draw (Graphics g) {

        for (int x = 0 ; x < 12 ; x++)
            for (int y = 0 ; y < 20 ; y++) {

                //System.out.println((y));
                g.drawRect(hitBox[x][y].x
                        , hitBox[x][y].y, TILE_SIZE , TILE_SIZE );
              //  System.out.println("HitBox x: " + hitBox[x][y].x  + " x : " + x);
                //  System.out.println("HitBox y: " + hitBox[x][y].y  + " y : " + y);
            }
    }

}
