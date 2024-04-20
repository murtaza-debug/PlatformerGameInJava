package Loader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Load {


    public static BufferedImage Image(String fileName) {

        InputStream is = Load.class.getResourceAsStream("/" + fileName);
        BufferedImage image ;

        try {
            System.out.println(2);
            image = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

}
