package Tiles;

import Entities.Player;

import java.awt.*;

import static MAINGAME.Panel.*;
import static Tiles.TileConstants.*;

public class TileManager extends Defaults{

    private int[][] map1 = TileMap.GetMap() ;
    TileHitBox hitBox ;
    Player player ;

    public TileManager (Player player)
    {
        this.player = new Player();
        hitBox = new TileHitBox(player , map1);
    }
    @Override
    public void update() {
        hitBox.updateTileHitBox();
    }

    @Override
    public void draw(Graphics g) {

        for (int x = 0 ; x < map1.length ; x++) {
            for (int y = 0; y < map1[x].length; y++) {
                if (map1[x][y] == GRASS) {
                    g.setColor(Color.BLACK);
                    g.fillRect(y * TILE_SIZE, x * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
                if (map1[x][y] == WALL) {
                    g.setColor(Color.GRAY);
                    g.fillRect(y * TILE_SIZE, x * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
                if (map1[x][y] == SKYE) {
                    g.setColor(Color.blue);
                    g.fillRect(y * TILE_SIZE, x * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }
        hitBox.draw(g);

    }



}
