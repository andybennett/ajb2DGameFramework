package ajb.examples.helpers;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import ajb.random.RandomInt;

public class ShipBuilderUtils {

	public static void main(String[] args) {

		ShipBuilderUtils.generateShips(100);

	}

	public static void generateShips(final int amount) {

		for (int i = 0; i < amount; i++) {

			Area area = generateShip();

			GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
					.getDefaultConfiguration();
			BufferedImage img = gc.createCompatibleImage((int) area.getBounds2D().getMaxX(),
					(int) area.getBounds2D().getMaxY(), BufferedImage.TYPE_INT_ARGB);

			Graphics2D gr = (Graphics2D) img.getGraphics();

			gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			gr.setColor(Colours.gray);
			gr.fill(area);
			gr.dispose();
			
			ImageUtils.save(img, "png", "Ship" + i);

		}

	}

	public static Area generateLevel1Cannon() {
		
		Area area = new Area();
		
		area.add(new Area(new Rectangle2D.Double(0, 0, 2, 100)));
		area.add(new Area(new Rectangle2D.Double(2, 1, 2, 100)));

		for (int x = 0; x < RandomInt.anyRandomIntRange(5, 5); x++) {

			addHull(area);

		}

		area.add(mirrorAlongX((int)area.getBounds2D().getMinX(), area));
		area = translateToTopLeft(area);

		return area;

	}	
	
	public static Area generateSmallShip() {
		
		Area area = new Area();
		
		area.add(new Area(new Rectangle2D.Double(0, 0, 5, 60)));

		for (int x = 0; x < RandomInt.anyRandomIntRange(100, 300); x++) {

			addHull(area);

		}

		area.add(mirrorAlongX((int)area.getBounds2D().getMinX(), area));
		area = translateToTopLeft(area);

		return area;

	}	
	
	public static Area generateShip() {
		
		Area area = generateHull();
		
		int combineAmount = RandomInt.anyRandomIntRange(0, 5);
		
		for (int i = 0; i < combineAmount; i++) {
			
			Point2D.Double point = findPointWithinArea(area);
			Area areaToAdd = generateHull();
			areaToAdd = translateToPoint(areaToAdd, point);
			area.add(areaToAdd);
			
		}

		area.add(mirrorAlongX(RandomInt.anyRandomIntRange((int)area.getBounds2D().getMinX(), (int)area.getBounds2D().getCenterX()), area));
		area = translateToTopLeft(area);

		return area;

	}

	public static void addStartingPoints(Area area) {

		for (int i = 0; i < 2; i++) {
			
			area.add(new Area(new Rectangle2D.Double(RandomInt.anyRandomIntRange(0, 10),
					RandomInt.anyRandomIntRange(0, 100), RandomInt.anyRandomIntRange(1, 10), RandomInt.anyRandomIntRange(10, 100))));
			
		}

	}
	
	public static Area generateHull() {
		
		Area area = new Area();

		addStartingPoints(area);

		for (int x = 0; x < RandomInt.anyRandomIntRange(50, 1000); x++) {

			addHull(area);

		}
		
		return area;
		
	}

	public static void addHull(Area area) {

		Point2D.Double point = findPointWithinArea(area);
		
		area.add(new Area(new Rectangle2D.Double(point.x + RandomInt.anyRandomIntRange(-10, 10),
				point.y + RandomInt.anyRandomIntRange(-10, 10), RandomInt.anyRandomIntRange(1, 10),
				RandomInt.anyRandomIntRange(1, 30))));

	}

	public static Point2D.Double findPointWithinArea(Area area) {

		Point2D.Double point = new Point2D.Double(
				RandomInt.anyRandomIntRange((int) area.getBounds2D().getMinX(), (int) area.getBounds2D().getMaxX()),
				RandomInt.anyRandomIntRange((int) area.getBounds2D().getMinY(), (int) area.getBounds2D().getMaxY()));

		while (!area.contains(point)) {

			point = new Point2D.Double(
					RandomInt.anyRandomIntRange((int) area.getBounds2D().getMinX(), (int) area.getBounds2D().getMaxX()),
					RandomInt.anyRandomIntRange((int) area.getBounds2D().getMinY(),
							(int) area.getBounds2D().getMaxY()));

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
