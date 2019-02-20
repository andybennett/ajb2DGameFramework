package ajb.examples.helpers;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

import ajb.random.RandomInt;

public class VesselUtils {

	public static void main(String[] args) {

		VesselUtils.generateMultiple(10);

	}

	public static void generateMultiple(final int amount) {

		for (int i = 0; i < amount; i++) {

			Area result = generate();

			result.add(AreaUtils.mirrorAlongX(0, result));
			result = AreaUtils.translateToTopLeft(result);

			BufferedImage img = ImageUtils.create(result.getBounds2D().getMaxX(), result.getBounds2D().getMaxY());

			Graphics2D gr = (Graphics2D) img.getGraphics();

			gr.setColor(Colours.gray);

			gr.fill(result);

			gr.setColor(Colours.makeTransparent(Colours.background, 60));
			gr.setStroke(new BasicStroke(5));
			gr.draw(result);

			gr.dispose();

			ImageUtils.save(img, "png", "Vessel" + i);

		}

	}

	public static Area generate() {

		Area result = AreaUtils.randomTriangularShape();

		int count = RandomInt.anyRandomIntRange(5, (int) result.getBounds2D().getMaxY());

		for (int i = 0; i < count; i++) {

			if (RandomInt.anyRandomIntRange(0, 1) == 1) {

				AreaUtils.addRandomBlock(result);

			} else {

				AreaUtils.subtractRandomBlock(result);
			}

		}

		return result;

	}
}
