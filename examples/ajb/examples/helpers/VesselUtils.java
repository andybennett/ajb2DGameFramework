package ajb.examples.helpers;

import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
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

			gr.setColor(Colours.background);
			gr.fillRect(0, 0, img.getWidth(), img.getHeight());

			gr.setColor(Colours.gray);

			gr.fill(result);

			gr.dispose();

			ImageUtils.save(img, "png", "Vessel" + i);

		}

	}

	public static Area generate() {

		Area result = createSegment();

		int count = RandomInt.anyRandomIntRange(0, 6);

		for (int i = 0; i < count; i++) {

			Area segment = createSegment();
			segment = AreaUtils.translateToPoint(segment,
					new Point2D.Double(RandomInt.anyRandomIntRange(0, 0), RandomInt.anyRandomIntRange(
							(int) result.getBounds2D().getCenterY(), (int) result.getBounds2D().getCenterY() + 40)));
			result.add(segment);

		}

		result = AreaUtils.getOutline(result);

		return result;

	}

	public static Area createSegment() {

		Area result = new Area(new Rectangle2D.Double(0, 0, RandomInt.anyRandomIntRange(10, 20),
				RandomInt.anyRandomIntRange(20, 200)));

		int count = RandomInt.anyRandomIntRange(100, 800);

		for (int i = 0; i < count; i++) {

			if (RandomInt.anyRandomIntRange(0, 5) >= 1) {

				AreaUtils.addRandomBlock(result);

			} else {

				AreaUtils.subtractRandomBlock(result);
			}

		}

		return result;

	}
}
