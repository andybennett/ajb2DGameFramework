package ajb.examples.helpers;

import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

import ajb.random.RandomInt;

public class AsteroidUtils {

	public static void main(String[] args) {

		AsteroidUtils.generateMultiple(10);

	}

	public static void generateMultiple(final int amount) {

		for (int i = 0; i < amount; i++) {

			Area result = generate();

			result = AreaUtils.translateToTopLeft(result);						

			BufferedImage img = ImageUtils.create(result.getBounds2D().getMaxX(), result.getBounds2D().getMaxY());

			Graphics2D gr = (Graphics2D) img.getGraphics();

			gr.setColor(Colours.background);
			gr.fillRect(0, 0, img.getWidth(), img.getHeight());			
			
			gr.setColor(Colours.gray);

			gr.fill(result);

			gr.dispose();

			ImageUtils.save(img, "png", "Asteroid" + i);

		}

	}

	public static Area generate() {

		Area result = AreaUtils.randomCircular();
		
		int count = RandomInt.anyRandomIntRange(0, 10);

		for (int i = 0; i < count; i++) {

			Area area = AreaUtils.randomCircular();
			area = AreaUtils.translateToPoint(area,
					AreaUtils.findRandomPointWithinArea(result));
			result.add(area);
		}
		
		randomise(result);

		return result;

	}
	
	public static void randomise(Area area) {
		
		int count = RandomInt.anyRandomIntRange(0, (int)area.getBounds2D().getMaxY());

		for (int i = 0; i < count; i++) {
			
			AreaUtils.subtractRandomCircle(area);
			
		}
	}	

}
