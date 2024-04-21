package Animations;

import Tiles.Props;

import java.awt.*;

public class PropAnimations {

    int aniTick , aniIndex , aniSpeed = 7;
    public Props props ;

    public PropAnimations(int x, int y, int width, int height, int type){
        props = new Props(x,y,width,height,type); ;
    }

    protected void updateAnimationTick()
    {
        aniTick++;
        if(aniTick >= aniSpeed)
        {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= props.propImage.length)
            {
                aniIndex = 0;
            }
        }
    }

    public void update ()
    {
        updateAnimationTick();
    }
    public void draw (Graphics g)
    {
        g.drawImage(props.propImage[aniIndex],props.x,props.y,props.width - 10,props.height + 20,null );
    }
}
