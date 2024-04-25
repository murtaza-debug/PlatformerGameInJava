package Tiles;

import Loader.Load;

import java.awt.image.BufferedImage;

import static Tiles.propConstants.GetAmount;

public class Props {
    public int x ;
    public int y ;
    public int width ;
    public int height ;
    public int type;

    public BufferedImage[] propImage;

    public Props(int x, int y, int width, int height, int type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
        propImage = new BufferedImage[GetAmount(type)];
        loadProps();
    }

    void loadProps()
    {
        for (int i = 0; i < GetAmount(type) ; i++) {
            if (i< 10) {
                propImage[i] = Load.Image("Vegetation/Grass2/Grass2_0000" + i + ".png");
            }
            else {
                propImage[i] = Load.Image("Vegetation/Grass2/Grass2_000" + i + ".png");
            }
        }
    }

    BufferedImage[] getPropImages() {
        return propImage;
    }
}
