package ajb.examples.helpers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import ajb.random.RandomInt;

public class ShipBuilderUtils {

	public static void main(String[] args) {

		ShipBuilderUtils.generateShips(10);

	}

	public static void generateShips(final int amount) {

		for (int i = 0; i < amount; i++) {

			Area ship = generate();

			GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
					.getDefaultConfiguration();
			BufferedImage img = gc.createCompatibleImage((int) ship.getBounds2D().getMaxX(),
					(int) ship.getBounds2D().getMaxY(), BufferedImage.TYPE_INT_ARGB);

			Graphics2D gr = (Graphics2D) img.getGraphics();

			gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			gr.setColor(Color.decode("#242424").brighter());
			gr.fill(ship);
			gr.dispose();

			ImageUtils.save(img, "png", "Ship" + i);

		}

	}

	public static BufferedImage generateImage() {

		Area ship1 = generate();

		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();
		BufferedImage img = gc.createCompatibleImage((int) ship1.getBounds2D().getMaxX(),
				(int) ship1.getBounds2D().getMaxY(), BufferedImage.TYPE_INT_ARGB);

		Graphics2D gr = (Graphics2D) img.getGraphics();

		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gr.setColor(Colours.gray);
		gr.fill(ship1);
		gr.dispose();

		return img;

	}

	public static Area generate() {

		Area area = null;

		int type = RandomInt.anyRandomIntRange(1, 2);

		switch (type) {
		// case 1:
		// area = halfCircle();
		// break;
		case 1:
			area = halfTriangle();
			break;
		// case 3:
		// area = halfCircleAlternative();
		// break;
		case 2:
			area = halfTriangleAlternative();
			break;
		}

		int count = RandomInt.anyRandomIntRange(20, 100);

		for (int i = 0; i < count; i++) {

			if (RandomInt.anyRandomIntRange(0, 1) == 1) {

				add(area);

			} else {

				subtract(area);
			}

		}

		return area;

	}

	public static Area halfTriangle() {

		int[] xpoints = new int[] { 0, 20, 0 };
		int[] ypoints = new int[] { 0, 70, 70 };

		return new Area(new Polygon(xpoints, ypoints, 3));

	}

	public static Area halfTriangleAlternative() {

		int[] xpoints = new int[] { 0, 40, 0 };
		int[] ypoints = new int[] { 0, 170, 170 };

		return new Area(new Polygon(xpoints, ypoints, 3));

	}

	public static Area halfCircle() {

		return new Area(new Arc2D.Double(0, 0, 40, 40, 0, 90, Arc2D.PIE));

	}

	public static Area halfCircleAlternative() {

		Area area = new Area();
		area.add(new Area(new Arc2D.Double(0, 0, 30, 30, 0, 90, Arc2D.PIE)));
		area.add(new Area(new Rectangle2D.Double(30, 0, 5, 50)));

		return area;

	}

	public static void add(Area area) {

		Point2D.Double point = findPointWithinArea(area);

		area.add(new Area(new Rectangle2D.Double(point.x + RandomInt.anyRandomIntRange(-5, 5),
				point.y + RandomInt.anyRandomIntRange(-5, 5), RandomInt.anyRandomIntRange(1, 10),
				RandomInt.anyRandomIntRange(1, 20))));

	}

	public static void addSpine(Area area) {

		area.add(new Area(new Rectangle2D.Double(area.getBounds2D().getMinX(),
				RandomInt.anyRandomIntRange((int) area.getBounds2D().getCenterY(), (int) area.getBounds2D().getMinY()) - 20,
				RandomInt.anyRandomIntRange(1, 10), RandomInt.anyRandomIntRange(1, 20))));

	}

	public static void subtract(Area area) {

		Point2D.Double point = findPointWithinArea(area);

		area.subtract(new Area(new Rectangle2D.Double(point.x + RandomInt.anyRandomIntRange(-5, 5),
				point.y + RandomInt.anyRandomIntRange(-5, 5), RandomInt.anyRandomIntRange(1, 10),
				RandomInt.anyRandomIntRange(1, 20))));

	}

	public static Point2D.Double findPointWithinArea(Area area) {

		Point2D.Double point = new Point2D.Double(
				RandomInt.anyRandomIntRange((int) area.getBounds2D().getMinX(), (int) area.getBounds2D().getMaxX()),
				RandomInt.anyRandomIntRange((int) area.getBounds2D().getMinY(), (int) area.getBounds2D().getMaxY()));

		int count = 0;

		while (!area.contains(point)) {

			point = new Point2D.Double(
					RandomInt.anyRandomIntRange((int) area.getBounds2D().getMinX(), (int) area.getBounds2D().getMaxX()),
					RandomInt.anyRandomIntRange((int) area.getBounds2D().getMinY(),
							(int) area.getBounds2D().getMaxY()));

			count++;

			// Safeguard against infinite loops
			if (count == 1000) {
				break;
			}

		}

		return point;

	}

	public static Area mirrorAlongX(double x, Area area) {

		AffineTransform at = new AffineTransform();
		at.translate(x, 0);
		at.scale(-1, 1);
		at.translate(-x, 0);
		return new Area(at.createTransformedShape(area));

	}

	public static Area flipVertically(Area area) {

		AffineTransform at = new AffineTransform();
		at.scale(1, -1);
		return new Area(at.createTransformedShape(area));

	}

	public static Area translateToTopLeft(Area area) {

		AffineTransform at = new AffineTransform();
		at.translate(area.getBounds2D().getMinX() * -1, area.getBounds2D().getMinY() * -1);
		return area.createTransformedArea(at);

	}

	public static Area translateToPoint(Area area, Point2D.Double point) {

		AffineTransform at = new AffineTransform();
		at.translate(area.getBounds2D().getCenterX() - point.x, area.getBounds2D().getCenterY() - point.y);
		return area.createTransformedArea(at);

	}

}
