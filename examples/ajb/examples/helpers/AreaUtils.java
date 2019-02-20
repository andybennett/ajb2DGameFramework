package ajb.examples.helpers;

import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import ajb.random.RandomInt;

public class AreaUtils {

	public static Area randomCircular() {

		Area area = new Area();

		for (int i = 0; i < RandomInt.anyRandomIntRange(100, 400); i++) {

			int angle = RandomInt.anyRandomIntRange(0, 360);

			int x = (int) (area.getBounds2D().getCenterX()
					+ (int) (Math.cos(Math.toRadians(angle)) * RandomInt.anyRandomIntRange(0, 30)));
			int y = (int) (area.getBounds2D().getCenterY()
					+ (int) (Math.sin(Math.toRadians(angle)) * RandomInt.anyRandomIntRange(0, 30)));

			area.add(new Area(new Ellipse2D.Double(x, y, RandomInt.anyRandomIntRange(10, 14),
					RandomInt.anyRandomIntRange(10, 14))));
		}

		return area;

	}

	public static Area randomBlocks() {

		Area area = new Area();

		for (int i = 0; i < RandomInt.anyRandomIntRange(100, 400); i++) {

			int angle = RandomInt.anyRandomIntRange(0, 360);

			int x = (int) (area.getBounds2D().getCenterX()
					+ (int) (Math.cos(Math.toRadians(angle)) * RandomInt.anyRandomIntRange(0, 20)));
			int y = (int) (area.getBounds2D().getCenterY()
					+ (int) (Math.sin(Math.toRadians(angle)) * RandomInt.anyRandomIntRange(0, 20)));

			area.add(new Area(new Rectangle2D.Double(x, y, RandomInt.anyRandomIntRange(2, 6),
					RandomInt.anyRandomIntRange(6, 10))));
		}

		return area;

	}

	public static Area randomTriangularShape() {

		int points = RandomInt.anyRandomIntRange(5, 10);

		int[] xpoints = new int[points];
		int[] ypoints = new int[points];

		int x = 0;
		int y = 0;

		xpoints[0] = x;
		ypoints[0] = y;

		for (int i = 1; i < points - 1; i++) {

			x += RandomInt.anyRandomIntRange(0, 10);
			y += RandomInt.anyRandomIntRange(5, 20);
			
			xpoints[i] = x;
			ypoints[i] = y;			

		}

		xpoints[points - 1] = 0;
		ypoints[points - 1] = y;

		return new Area(new Polygon(xpoints, ypoints, points));

	}

	public static void addRandomBlock(Area area) {

		Point2D.Double point = AreaUtils.findRandomPointWithinArea(area);

		area.add(new Area(new Rectangle2D.Double(point.x + RandomInt.anyRandomIntRange(-1, 5),
				point.y + RandomInt.anyRandomIntRange(-1, 5), RandomInt.anyRandomIntRange(2, 6),
				RandomInt.anyRandomIntRange(6, 10))));

	}

	public static void addRandomBlockAlongMinX(Area area) {

		area.add(new Area(new Rectangle2D.Double(
				RandomInt.anyRandomIntRange((int) area.getBounds2D().getMinX(), (int) area.getBounds2D().getMinX()),
				RandomInt.anyRandomIntRange((int) area.getBounds2D().getMinY(), (int) area.getBounds2D().getCenterY()),
				RandomInt.anyRandomIntRange(2, 6), RandomInt.anyRandomIntRange(6, 10))));

	}

	public static void addRandomBlockAlongMaxX(Area area) {

		area.add(new Area(new Rectangle2D.Double(
				RandomInt.anyRandomIntRange((int) area.getBounds2D().getMaxX(), (int) area.getBounds2D().getMaxX()),
				RandomInt.anyRandomIntRange((int) area.getBounds2D().getMinY(), (int) area.getBounds2D().getMaxY()),
				RandomInt.anyRandomIntRange(2, 6), RandomInt.anyRandomIntRange(6, 10))));

	}

	public static void addRandomBlockAlongMinY(Area area) {

		area.add(new Area(new Rectangle2D.Double(
				RandomInt.anyRandomIntRange((int) area.getBounds2D().getMinX(), (int) area.getBounds2D().getMaxX()),
				RandomInt.anyRandomIntRange((int) area.getBounds2D().getMinY() - 5, (int) area.getBounds2D().getMinY()),
				RandomInt.anyRandomIntRange(2, 6), RandomInt.anyRandomIntRange(6, 10))));

	}

	public static void addRandomBlockAlongMaxY(Area area) {

		area.add(new Area(new Rectangle2D.Double(
				RandomInt.anyRandomIntRange((int) area.getBounds2D().getMinX(), (int) area.getBounds2D().getMaxX()),
				RandomInt.anyRandomIntRange((int) area.getBounds2D().getMaxY(), (int) area.getBounds2D().getMaxY()),
				RandomInt.anyRandomIntRange(2, 6), RandomInt.anyRandomIntRange(6, 10))));

	}

	public static void subtractRandomBlock(Area area) {

		Point2D.Double point = AreaUtils.findRandomPointWithinArea(area);

		area.subtract(new Area(new Rectangle2D.Double(point.x + RandomInt.anyRandomIntRange(-5, 5),
				point.y + RandomInt.anyRandomIntRange(-5, 5), RandomInt.anyRandomIntRange(1, 6),
				RandomInt.anyRandomIntRange(1, 10))));

	}

	public static void subtractRandomCircle(Area area) {

		Point2D.Double point = AreaUtils.findRandomPointWithinArea(area);

		area.subtract(new Area(new Ellipse2D.Double(point.x, point.y, RandomInt.anyRandomIntRange(1, 6),
				RandomInt.anyRandomIntRange(1, 6))));

	}

	public static Point2D.Double findRandomPointWithinArea(Area area) {

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
		at.translate(point.x, point.y);
		return area.createTransformedArea(at);

	}

}
