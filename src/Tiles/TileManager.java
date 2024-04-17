package Tiles;

import java.awt.*;

import static MAINGAME.Panel.TILE_SIZE;
import static Tiles.TileConstants.GRASS;
import static Tiles.TileConstants.WALL;

public class TileManager extends Defaults{

    private int[][] map1 = TileMap.GetMap() ;

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {

        for (int x= 0 ; x < map1.length ; x++)
            for (int y = 0 ; y < map1[x].length ; y++)
            {
                if (map1[x][y] == GRASS) {
                    g.setColor(Color.GREEN);
                    g.fillRect(y * TILE_SIZE, x * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
                if (map1[x][y] == WALL)
                {
                    g.setColor(Color.GRAY);
                    g.fillRect(y * TILE_SIZE, x * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
    }
}
