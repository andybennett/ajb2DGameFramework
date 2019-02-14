package ajb.examples.helpers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Vessel {

	BufferedImage img = null;

	Point2D.Double center = null;

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

	double rotationInDegrees = 60;

	final static float dash1[] = { 10.0f };
	final static BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1,
			0.0f);

	public Vessel(BufferedImage img, Point2D.Double center) {

		this.img = img;
		this.center = center;
		bounds = new Hex(this.center, "", img.getWidth() + 20);
		armour = new Hex(this.center, "", img.getWidth() + 28);
		shields = new Hex(this.center, "", img.getWidth() + 38);

		northShields = new Area(new Polygon(
				new int[] { (int) armour.getNorthWest().getX(), (int) shields.getNorthWest().getX(),
						(int) shields.getNorthEast().getX(), (int) armour.getNorthEast().getX() },
				new int[] { (int) armour.getNorthWest().getY(), (int) shields.getNorthWest().getY(),
						(int) shields.getNorthEast().getY(), (int) armour.getNorthEast().getY() },
				4));

		northEastShields = new Area(new Polygon(
				new int[] { (int) armour.getNorthEast().getX(), (int) shields.getNorthEast().getX(),
						(int) shields.getEast().getX(), (int) armour.getEast().getX() },
				new int[] { (int) armour.getNorthEast().getY(), (int) shields.getNorthEast().getY(),
						(int) shields.getEast().getY(), (int) armour.getEast().getY() },
				4));

		southEastShields = new Area(new Polygon(
				new int[] { (int) armour.getEast().getX(), (int) shields.getEast().getX(),
						(int) shields.getSouthEast().getX(), (int) armour.getSouthEast().getX() },
				new int[] { (int) armour.getEast().getY(), (int) shields.getEast().getY(),
						(int) shields.getSouthEast().getY(), (int) armour.getSouthEast().getY() },
				4));

		southShields = new Area(new Polygon(
				new int[] { (int) armour.getSouthEast().getX(), (int) shields.getSouthEast().getX(),
						(int) shields.getSouthWest().getX(), (int) armour.getSouthWest().getX() },
				new int[] { (int) armour.getSouthEast().getY(), (int) shields.getSouthEast().getY(),
						(int) shields.getSouthWest().getY(), (int) armour.getSouthWest().getY() },
				4));

		southWestShields = new Area(new Polygon(
				new int[] { (int) armour.getSouthWest().getX(), (int) shields.getSouthWest().getX(),
						(int) shields.getWest().getX(), (int) armour.getWest().getX() },
				new int[] { (int) armour.getSouthWest().getY(), (int) shields.getSouthWest().getY(),
						(int) shields.getWest().getY(), (int) armour.getWest().getY() },
				4));

		northWestShields = new Area(new Polygon(
				new int[] { (int) armour.getWest().getX(), (int) shields.getWest().getX(),
						(int) shields.getNorthWest().getX(), (int) armour.getNorthWest().getX() },
				new int[] { (int) armour.getWest().getY(), (int) shields.getWest().getY(),
						(int) shields.getNorthWest().getY(), (int) armour.getNorthWest().getY() },
				4));

		northArmour = new Area(new Polygon(
				new int[] { (int) bounds.getNorthWest().getX(), (int) armour.getNorthWest().getX(),
						(int) armour.getNorthEast().getX(), (int) bounds.getNorthEast().getX() },
				new int[] { (int) bounds.getNorthWest().getY(), (int) armour.getNorthWest().getY(),
						(int) armour.getNorthEast().getY(), (int) bounds.getNorthEast().getY() },
				4));

		northEastArmour = new Area(new Polygon(
				new int[] { (int) bounds.getNorthEast().getX(), (int) armour.getNorthEast().getX(),
						(int) armour.getEast().getX(), (int) bounds.getEast().getX() },
				new int[] { (int) bounds.getNorthEast().getY(), (int) armour.getNorthEast().getY(),
						(int) armour.getEast().getY(), (int) bounds.getEast().getY() },
				4));

		southEastArmour = new Area(new Polygon(
				new int[] { (int) bounds.getEast().getX(), (int) armour.getEast().getX(),
						(int) armour.getSouthEast().getX(), (int) bounds.getSouthEast().getX() },
				new int[] { (int) bounds.getEast().getY(), (int) armour.getEast().getY(),
						(int) armour.getSouthEast().getY(), (int) bounds.getSouthEast().getY() },
				4));

		southArmour = new Area(new Polygon(
				new int[] { (int) bounds.getSouthEast().getX(), (int) armour.getSouthEast().getX(),
						(int) armour.getSouthWest().getX(), (int) bounds.getSouthWest().getX() },
				new int[] { (int) bounds.getSouthEast().getY(), (int) armour.getSouthEast().getY(),
						(int) armour.getSouthWest().getY(), (int) bounds.getSouthWest().getY() },
				4));

		southWestArmour = new Area(new Polygon(
				new int[] { (int) bounds.getSouthWest().getX(), (int) armour.getSouthWest().getX(),
						(int) armour.getWest().getX(), (int) bounds.getWest().getX() },
				new int[] { (int) bounds.getSouthWest().getY(), (int) armour.getSouthWest().getY(),
						(int) armour.getWest().getY(), (int) bounds.getWest().getY() },
				4));

		northWestArmour = new Area(new Polygon(
				new int[] { (int) bounds.getWest().getX(), (int) armour.getWest().getX(),
						(int) armour.getNorthWest().getX(), (int) bounds.getNorthWest().getX() },
				new int[] { (int) bounds.getWest().getY(), (int) armour.getWest().getY(),
						(int) armour.getNorthWest().getY(), (int) bounds.getNorthWest().getY() },
				4));
	}

	public void draw(Graphics2D g2d) {

		g2d.rotate(Math.toRadians(rotationInDegrees), center.getX(), center.getY());

		// g2d.setStroke(dashed);

		// Boundary Hex
		// g2d.draw(bounds.getArea());
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
		g2d.setColor(getColor(50));
		g2d.fill(southShields);
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
		g2d.setColor(getColor(100));
		g2d.fill(southArmour);
		g2d.setColor(getColor(100));
		g2d.fill(southWestArmour);
		g2d.setColor(getColor(100));
		g2d.fill(northWestArmour);

		g2d.setColor(Color.BLACK);
		g2d.draw(northShields);
		g2d.draw(northEastShields);
		g2d.draw(southEastShields);
		g2d.draw(southShields);
		g2d.draw(southWestShields);
		g2d.draw(northWestShields);
		g2d.draw(northArmour);
		g2d.draw(northEastArmour);
		g2d.draw(southEastArmour);
		g2d.draw(southArmour);
		g2d.draw(southWestArmour);
		g2d.draw(northWestArmour);

	}

	public static Color makeTransparent(Color source, int alpha) {

		return new Color(source.getRed(), source.getGreen(), source.getBlue(), alpha);

	}

	public Color getColor(double percent) {
		double power = percent / 100;

		double H = power * 0.4; // Hue (note 0.4 = Green, see huge chart below)
		double S = 0.4; // Saturation
		double B = 0.3; // Brightness

		return Color.getHSBColor((float) H, (float) S, (float) B);
	}
}
