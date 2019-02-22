package ajb.examples.helpers;

import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import ajb.random.RandomInt;

public class AreaUtils {

	public static Area randomCircular() {

		Area area = new Area();

		for (int i = 0; i < RandomInt.anyRandomIntRange(100, 400); i++) {

			int angle = RandomInt.anyRandomIntRange(0, 360);

			int x = (int) (area.getBounds2D().getCenterX()
					+ (int) (Math.cos(Math.toRadians(angle)) * RandomInt.anyRandomIntRange(0, 50)));
			int y = (int) (area.getBounds2D().getCenterY()
					+ (int) (Math.sin(Math.toRadians(angle)) * RandomInt.anyRandomIntRange(0, 50)));

			area.add(new Area(new Ellipse2D.Double(x, y, RandomInt.anyRandomIntRange(20, 34),
					RandomInt.anyRandomIntRange(20, 34))));
		}

		return area;

	}

	public static Area randomBlocks() {

		Area area = new Area();

		for (int i = 0; i < RandomInt.anyRandomIntRange(100, 200); i++) {

			int angle = RandomInt.anyRandomIntRange(0, 360);

			int x = (int) (area.getBounds2D().getCenterX()
					+ (int) (Math.cos(Math.toRadians(angle)) * RandomInt.anyRandomIntRange(0, 10)));
			int y = (int) (area.getBounds2D().getCenterY()
					+ (int) (Math.sin(Math.toRadians(angle)) * RandomInt.anyRandomIntRange(0, 10)));

			area.add(new Area(new Rectangle2D.Double(x, y, RandomInt.anyRandomIntRange(1, 10),
					RandomInt.anyRandomIntRange(1, 20))));
		}

		return area;

	}

	public static void addRandomBlock(Area area) {

		Point2D.Double point = AreaUtils.findRandomPointWithinArea(area);

		area.add(new Area(new Rectangle2D.Double(point.x, point.y, RandomInt.anyRandomIntRange(1, 10),
				RandomInt.anyRandomIntRange(1, 40))));

	}

	public static void addRandomBlockAlongMinX(Area area) {

		area.add(new Area(new Rectangle2D.Double(
				RandomInt.anyRandomIntRange((int) area.getBounds2D().getMinX(), (int) area.getBounds2D().getMinX()),
				RandomInt.anyRandomIntRange((int) area.getBounds2D().getMinY(), (int) area.getBounds2D().getMaxY()),
				RandomInt.anyRandomIntRange(1, 10), RandomInt.anyRandomIntRange(2, 10))));

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

		area.subtract(new Area(new Rectangle2D.Double(point.x, point.y, RandomInt.anyRandomIntRange(1, 10),
				RandomInt.anyRandomIntRange(1, 20))));

	}

	public static void subtractRandomCircle(Area area) {

		Point2D.Double point = AreaUtils.findRandomPointWithinArea(area);

		area.subtract(new Area(new Ellipse2D.Double(point.x, point.y, RandomInt.anyRandomIntRange(1, 6),
				RandomInt.anyRandomIntRange(1, 6))));

	}

	public static void subtractRandomLine(Area area) {

		Point2D.Double point = AreaUtils.findRandomPointWithinArea(area);

		int vertical = RandomInt.anyRandomIntRange(0, 4);

		if (vertical < 3) {

			area.subtract(new Area(new Rectangle2D.Double(point.x, point.y, RandomInt.anyRandomIntRange(1, 1),
					RandomInt.anyRandomIntRange(1, 20))));

		} else {

			area.subtract(new Area(new Rectangle2D.Double(point.x, point.y, RandomInt.anyRandomIntRange(1, 20),
					RandomInt.anyRandomIntRange(1, 1))));

		}
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

	public static Area getOutline(Area area) {
		Area ret = new Area();
		double[] coords = new double[6];
		GeneralPath tmpPath = new GeneralPath();

		PathIterator pathIterator = area.getPathIterator(null);
		for (; !pathIterator.isDone(); pathIterator.next()) {
			int type = pathIterator.currentSegment(coords);
			switch (type) {
			case PathIterator.WIND_EVEN_ODD:
				tmpPath.reset();
				tmpPath.moveTo(coords[0], coords[1]);
				break;
			case PathIterator.SEG_LINETO:
				tmpPath.lineTo(coords[0], coords[1]);
				break;
			case PathIterator.SEG_QUADTO:
				tmpPath.quadTo(coords[0], coords[1], coords[2], coords[3]);
				break;
			case PathIterator.SEG_CUBICTO:
				tmpPath.curveTo(coords[0], coords[1], coords[2], coords[3], coords[4], coords[5]);
				break;
			case PathIterator.SEG_CLOSE:
				ret.add(new Area(tmpPath));
				break;
			default:
				System.err.println("Unhandled type " + type);
				break;
			}
		}

		return ret;
	}

}
