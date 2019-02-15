package ajb.examples.helpers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Vessel {

	public BufferedImage img = null;

	public Point2D.Double center = null;

	Hex bounds = null;
	Hex armour = null;
	Hex shields = null;

	Area northShields = null;
	Area northEastShields = null;
	Area southEastShields = null;
	Area southShields = null;
	Area southWestShields = null;
	Area northWestShields = null;
	Area northArmour = null;
	Area northEastArmour = null;
	Area southEastArmour = null;
	Area southArmour = null;
	Area southWestArmour = null;
	Area northWestArmour = null;

	double rotationInDegrees = 0;

	final static float dash1[] = { 10.0f };
	final static BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1,
			0.0f);

	public Vessel(BufferedImage img, Point2D.Double center) {

		this.img = img;
		this.center = center;
		
		int size = img.getHeight() > img.getWidth() ? img.getHeight() : img.getWidth();
		
		bounds = new Hex(this.center, "", size);
		armour = new Hex(this.center, "", size + 10);
		shields = new Hex(this.center, "", size + 25);

		northShields = new Area(new Polygon(
				new int[] { (int) armour.getNorthWestPoint().getX(), (int) shields.getNorthWestPoint().getX(),
						(int) shields.getNorthEastPoint().getX(), (int) armour.getNorthEastPoint().getX() },
				new int[] { (int) armour.getNorthWestPoint().getY(), (int) shields.getNorthWestPoint().getY(),
						(int) shields.getNorthEastPoint().getY(), (int) armour.getNorthEastPoint().getY() },
				4));

		northEastShields = new Area(new Polygon(
				new int[] { (int) armour.getNorthEastPoint().getX(), (int) shields.getNorthEastPoint().getX(),
						(int) shields.getEastPoint().getX(), (int) armour.getEastPoint().getX() },
				new int[] { (int) armour.getNorthEastPoint().getY(), (int) shields.getNorthEastPoint().getY(),
						(int) shields.getEastPoint().getY(), (int) armour.getEastPoint().getY() },
				4));

		southEastShields = new Area(new Polygon(
				new int[] { (int) armour.getEastPoint().getX(), (int) shields.getEastPoint().getX(),
						(int) shields.getSouthEastPoint().getX(), (int) armour.getSouthEastPoint().getX() },
				new int[] { (int) armour.getEastPoint().getY(), (int) shields.getEastPoint().getY(),
						(int) shields.getSouthEastPoint().getY(), (int) armour.getSouthEastPoint().getY() },
				4));

//		southShields = new Area(new Polygon(
//				new int[] { (int) armour.getSouthEastPoint().getX(), (int) shields.getSouthEastPoint().getX(),
//						(int) shields.getSouthWestPoint().getX(), (int) armour.getSouthWestPoint().getX() },
//				new int[] { (int) armour.getSouthEastPoint().getY(), (int) shields.getSouthEastPoint().getY(),
//						(int) shields.getSouthWestPoint().getY(), (int) armour.getSouthWestPoint().getY() },
//				4));

		southWestShields = new Area(new Polygon(
				new int[] { (int) armour.getSouthWestPoint().getX(), (int) shields.getSouthWestPoint().getX(),
						(int) shields.getWestPoint().getX(), (int) armour.getWestPoint().getX() },
				new int[] { (int) armour.getSouthWestPoint().getY(), (int) shields.getSouthWestPoint().getY(),
						(int) shields.getWestPoint().getY(), (int) armour.getWestPoint().getY() },
				4));

		northWestShields = new Area(new Polygon(
				new int[] { (int) armour.getWestPoint().getX(), (int) shields.getWestPoint().getX(),
						(int) shields.getNorthWestPoint().getX(), (int) armour.getNorthWestPoint().getX() },
				new int[] { (int) armour.getWestPoint().getY(), (int) shields.getWestPoint().getY(),
						(int) shields.getNorthWestPoint().getY(), (int) armour.getNorthWestPoint().getY() },
				4));

		northArmour = new Area(new Polygon(
				new int[] { (int) bounds.getNorthWestPoint().getX(), (int) armour.getNorthWestPoint().getX(),
						(int) armour.getNorthEastPoint().getX(), (int) bounds.getNorthEastPoint().getX() },
				new int[] { (int) bounds.getNorthWestPoint().getY(), (int) armour.getNorthWestPoint().getY(),
						(int) armour.getNorthEastPoint().getY(), (int) bounds.getNorthEastPoint().getY() },
				4));

		northEastArmour = new Area(new Polygon(
				new int[] { (int) bounds.getNorthEastPoint().getX(), (int) armour.getNorthEastPoint().getX(),
						(int) armour.getEastPoint().getX(), (int) bounds.getEastPoint().getX() },
				new int[] { (int) bounds.getNorthEastPoint().getY(), (int) armour.getNorthEastPoint().getY(),
						(int) armour.getEastPoint().getY(), (int) bounds.getEastPoint().getY() },
				4));

		southEastArmour = new Area(new Polygon(
				new int[] { (int) bounds.getEastPoint().getX(), (int) armour.getEastPoint().getX(),
						(int) armour.getSouthEastPoint().getX(), (int) bounds.getSouthEastPoint().getX() },
				new int[] { (int) bounds.getEastPoint().getY(), (int) armour.getEastPoint().getY(),
						(int) armour.getSouthEastPoint().getY(), (int) bounds.getSouthEastPoint().getY() },
				4));

//		southArmour = new Area(new Polygon(
//				new int[] { (int) bounds.getSouthEastPoint().getX(), (int) armour.getSouthEastPoint().getX(),
//						(int) armour.getSouthWestPoint().getX(), (int) bounds.getSouthWestPoint().getX() },
//				new int[] { (int) bounds.getSouthEastPoint().getY(), (int) armour.getSouthEastPoint().getY(),
//						(int) armour.getSouthWestPoint().getY(), (int) bounds.getSouthWestPoint().getY() },
//				4));

		southWestArmour = new Area(new Polygon(
				new int[] { (int) bounds.getSouthWestPoint().getX(), (int) armour.getSouthWestPoint().getX(),
						(int) armour.getWestPoint().getX(), (int) bounds.getWestPoint().getX() },
				new int[] { (int) bounds.getSouthWestPoint().getY(), (int) armour.getSouthWestPoint().getY(),
						(int) armour.getWestPoint().getY(), (int) bounds.getWestPoint().getY() },
				4));

		northWestArmour = new Area(new Polygon(
				new int[] { (int) bounds.getWestPoint().getX(), (int) armour.getWestPoint().getX(),
						(int) armour.getNorthWestPoint().getX(), (int) bounds.getNorthWestPoint().getX() },
				new int[] { (int) bounds.getWestPoint().getY(), (int) armour.getWestPoint().getY(),
						(int) armour.getNorthWestPoint().getY(), (int) bounds.getNorthWestPoint().getY() },
				4));
	}

	public void draw(Graphics2D g2d) {

		g2d.rotate(Math.toRadians(rotationInDegrees), center.getX(), center.getY());

		// g2d.setStroke(dashed);

		// Boundary Hex
		g2d.setColor(Color.decode("#242424"));
		 g2d.fill(bounds.getPoly());
		// g2d.draw(armour.getArea());
		// g2d.draw(shields.getArea());

		g2d.drawImage(img, null, (int) center.getX() - (img.getWidth() / 2),
				(int) center.getY() - (img.getHeight() / 2));

		g2d.setColor(getColor(100));
		g2d.fill(northShields);
		g2d.setColor(getColor(80));
		g2d.fill(northEastShields);
		g2d.setColor(getColor(60));
		g2d.fill(southEastShields);
//		g2d.setColor(getColor(50));
//		g2d.fill(southShields);
		g2d.setColor(getColor(20));
		g2d.fill(southWestShields);
		g2d.setColor(getColor(0));
		g2d.fill(northWestShields);

		g2d.setColor(getColor(100));
		g2d.fill(northArmour);
		g2d.setColor(getColor(100));
		g2d.fill(northEastArmour);
		g2d.setColor(getColor(100));
		g2d.fill(southEastArmour);
//		g2d.setColor(getColor(100));
//		g2d.fill(southArmour);
		g2d.setColor(getColor(100));
		g2d.fill(southWestArmour);
		g2d.setColor(getColor(100));
		g2d.fill(northWestArmour);

		g2d.setColor(Color.BLACK);
		g2d.draw(northShields);
		g2d.draw(northEastShields);
		g2d.draw(southEastShields);
//		g2d.draw(southShields);
		g2d.draw(southWestShields);
		g2d.draw(northWestShields);
		g2d.draw(northArmour);
		g2d.draw(northEastArmour);
		g2d.draw(southEastArmour);
//		g2d.draw(southArmour);
		g2d.draw(southWestArmour);
		g2d.draw(northWestArmour);

	}
	
	public void drawDisplay(Graphics2D g2d) {
		
		g2d.setColor(Color.decode("#242424"));
		g2d.fillRect(0, 0, img.getWidth() * 2 + 20, img.getHeight() * 2 + 20);
		
		g2d.scale(2, 2);
		g2d.drawImage(img, null, 10, 10);
		
	}

	public static Color makeTransparent(Color source, int alpha) {

		return new Color(source.getRed(), source.getGreen(), source.getBlue(), alpha);

	}

	public Color getColor(double percent) {
		double power = percent / 100;

		double H = power * 0.4; // Hue (note 0.4 = Green, see huge chart below)
		double S = 0.3; // Saturation
		double B = 0.3; // Brightness

		return Color.getHSBColor((float) H, (float) S, (float) B);
	}
}
