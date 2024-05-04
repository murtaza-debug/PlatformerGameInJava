package Loader;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Load {


    public static BufferedImage Image(String fileName) {

        InputStream is = Load.class.getResourceAsStream("/" + fileName);
        BufferedImage image ;

        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    public static Clip Audio (String fileName)
    {
        URL url = Load.class.getResource("/" + fileName);
        Clip clip = null;
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip() ;
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clip ;
    }

}
