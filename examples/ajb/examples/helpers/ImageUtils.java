package ajb.examples.helpers;

import java.awt.image.BufferedImage;
import java.io.File;
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
    
	public static void save(BufferedImage image, String ext, String fileName) {

		File file = new File(fileName + "." + ext);

		try {
			
			ImageIO.write(image, ext, file);
			
		} catch (IOException e) {
			
			System.out.println("Write error for " + file.getPath() + ": " + e.getMessage());
			
		}
		
	}

}
