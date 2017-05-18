package ajb.examples.helpers;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {

    public BufferedImage getImage(String image) {

        BufferedImage result = null;

        try {

            ImageIO.read(this.getClass().getResourceAsStream(image));

        } catch (IOException e) {

            e.printStackTrace();

        }

        return result;

    }

}
