package Tiles;

import Loader.Load;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static MAINGAME.Panel.*;

public class TileManager  {

    public int[][] map1 = TileMap.GetMap();

    public ArrayList<Tile> tiles = new ArrayList<>();
    //public ArrayList<PropAnimations> propAnimations = new ArrayList<>();
    int x = 0;


    BufferedImage Map ;
    public static BufferedImage background;

    public TileManager() {
        Map = Load.Image("Map.png");
        background = Load.Image("bg.jpg");
        makeTiles();
    }
    public void makeTiles() {

        int k = 0;
        Color color ;
        for (int i = 0 ; i < 12; i++) {
            for (int j = 0 ; j < 200; j++) {
                color = new Color(Map.getRGB((j* TILE_SIZE +  (j+1)*TILE_SIZE ) / 2,(i * TILE_SIZE +  (i+1)*TILE_SIZE ) / 2) );
                if ((color.getRed() != 0)) {
                    k++;
                    tiles.add(new Tile(j*TILE_SIZE,i*TILE_SIZE, TILE_SIZE , TILE_SIZE));
                }
            }
        }

    }

    public void update(int xOffset) {

        for (Tile tile : tiles)
            tile.updateTileHitBox(xOffset);

    }




    public void draw(Graphics2D g , int xOffSet) {

            g.drawImage(background,0,0, 1280,768,null);

            g.drawImage(Map , - xOffSet, 0, null);


    }


}

