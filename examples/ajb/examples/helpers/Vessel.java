package ajb.examples.helpers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Map;

public class Vessel {

	public BufferedImage img = null;
	public Area area = null;

	public Point2D.Double center = null;

	Hex boundsHex = null;
	Hex armourHex = null;
	Hex shieldsHex = null;

	Area shields = null;
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

	double hull = 0;

	final static float dash1[] = { 10.0f };
	final static BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1,
			0.0f);

	public Vessel(Area area, Point2D.Double center) {

		this.area = area;
		this.center = center;

		generateImage();
		calculateBounds();

	}

	public void calculateBounds() {

		if (this.img != null) {

			int size = img.getHeight() > img.getWidth() ? img.getHeight() : img.getWidth();

			boundsHex = new Hex(this.center, "", size);
			armourHex = new Hex(this.center, "", size + 20);
			shieldsHex = new Hex(this.center, "", size + 30);

			northShields = new Area(new Polygon(
					new int[] { (int) armourHex.getNorthWestPoint().getX(), (int) shieldsHex.getNorthWestPoint().getX(),
							(int) shieldsHex.getNorthEastPoint().getX(), (int) armourHex.getNorthEastPoint().getX() },
					new int[] { (int) armourHex.getNorthWestPoint().getY(), (int) shieldsHex.getNorthWestPoint().getY(),
							(int) shieldsHex.getNorthEastPoint().getY(), (int) armourHex.getNorthEastPoint().getY() },
					4));

			northEastShields = new Area(new Polygon(
					new int[] { (int) armourHex.getNorthEastPoint().getX(), (int) shieldsHex.getNorthEastPoint().getX(),
							(int) shieldsHex.getEastPoint().getX(), (int) armourHex.getEastPoint().getX() },
					new int[] { (int) armourHex.getNorthEastPoint().getY(), (int) shieldsHex.getNorthEastPoint().getY(),
							(int) shieldsHex.getEastPoint().getY(), (int) armourHex.getEastPoint().getY() },
					4));

			southEastShields = new Area(new Polygon(
					new int[] { (int) armourHex.getEastPoint().getX(), (int) shieldsHex.getEastPoint().getX(),
							(int) shieldsHex.getSouthEastPoint().getX(), (int) armourHex.getSouthEastPoint().getX() },
					new int[] { (int) armourHex.getEastPoint().getY(), (int) shieldsHex.getEastPoint().getY(),
							(int) shieldsHex.getSouthEastPoint().getY(), (int) armourHex.getSouthEastPoint().getY() },
					4));

			// southShields = new Area(new Polygon(
			// new int[] { (int) armour.getSouthEastPoint().getX(), (int)
			// shields.getSouthEastPoint().getX(),
			// (int) shields.getSouthWestPoint().getX(), (int)
			// armour.getSouthWestPoint().getX() },
			// new int[] { (int) armour.getSouthEastPoint().getY(), (int)
			// shields.getSouthEastPoint().getY(),
			// (int) shields.getSouthWestPoint().getY(), (int)
			// armour.getSouthWestPoint().getY() },
			// 4));

			southWestShields = new Area(new Polygon(
					new int[] { (int) armourHex.getSouthWestPoint().getX(), (int) shieldsHex.getSouthWestPoint().getX(),
							(int) shieldsHex.getWestPoint().getX(), (int) armourHex.getWestPoint().getX() },
					new int[] { (int) armourHex.getSouthWestPoint().getY(), (int) shieldsHex.getSouthWestPoint().getY(),
							(int) shieldsHex.getWestPoint().getY(), (int) armourHex.getWestPoint().getY() },
					4));

			northWestShields = new Area(new Polygon(
					new int[] { (int) armourHex.getWestPoint().getX(), (int) shieldsHex.getWestPoint().getX(),
							(int) shieldsHex.getNorthWestPoint().getX(), (int) armourHex.getNorthWestPoint().getX() },
					new int[] { (int) armourHex.getWestPoint().getY(), (int) shieldsHex.getWestPoint().getY(),
							(int) shieldsHex.getNorthWestPoint().getY(), (int) armourHex.getNorthWestPoint().getY() },
					4));

			shields = new Area();
			shields.add(northShields);
			shields.add(northEastShields);
			shields.add(southEastShields);
			shields.add(southWestShields);
			shields.add(northWestShields);

			northArmour = new Area(new Polygon(
					new int[] { (int) boundsHex.getNorthWestPoint().getX(), (int) armourHex.getNorthWestPoint().getX(),
							(int) armourHex.getNorthEastPoint().getX(), (int) boundsHex.getNorthEastPoint().getX() },
					new int[] { (int) boundsHex.getNorthWestPoint().getY(), (int) armourHex.getNorthWestPoint().getY(),
							(int) armourHex.getNorthEastPoint().getY(), (int) boundsHex.getNorthEastPoint().getY() },
					4));

			northEastArmour = new Area(new Polygon(
					new int[] { (int) boundsHex.getNorthEastPoint().getX(), (int) armourHex.getNorthEastPoint().getX(),
							(int) armourHex.getEastPoint().getX(), (int) boundsHex.getEastPoint().getX() },
					new int[] { (int) boundsHex.getNorthEastPoint().getY(), (int) armourHex.getNorthEastPoint().getY(),
							(int) armourHex.getEastPoint().getY(), (int) boundsHex.getEastPoint().getY() },
					4));

			southEastArmour = new Area(new Polygon(
					new int[] { (int) boundsHex.getEastPoint().getX(), (int) armourHex.getEastPoint().getX(),
							(int) armourHex.getSouthEastPoint().getX(), (int) boundsHex.getSouthEastPoint().getX() },
					new int[] { (int) boundsHex.getEastPoint().getY(), (int) armourHex.getEastPoint().getY(),
							(int) armourHex.getSouthEastPoint().getY(), (int) boundsHex.getSouthEastPoint().getY() },
					4));

			// southArmour = new Area(new Polygon(
			// new int[] { (int) bounds.getSouthEastPoint().getX(), (int)
			// armour.getSouthEastPoint().getX(),
			// (int) armour.getSouthWestPoint().getX(), (int)
			// bounds.getSouthWestPoint().getX() },
			// new int[] { (int) bounds.getSouthEastPoint().getY(), (int)
			// armour.getSouthEastPoint().getY(),
			// (int) armour.getSouthWestPoint().getY(), (int)
			// bounds.getSouthWestPoint().getY() },
			// 4));

			southWestArmour = new Area(new Polygon(
					new int[] { (int) boundsHex.getSouthWestPoint().getX(), (int) armourHex.getSouthWestPoint().getX(),
							(int) armourHex.getWestPoint().getX(), (int) boundsHex.getWestPoint().getX() },
					new int[] { (int) boundsHex.getSouthWestPoint().getY(), (int) armourHex.getSouthWestPoint().getY(),
							(int) armourHex.getWestPoint().getY(), (int) boundsHex.getWestPoint().getY() },
					4));

			northWestArmour = new Area(new Polygon(
					new int[] { (int) boundsHex.getWestPoint().getX(), (int) armourHex.getWestPoint().getX(),
							(int) armourHex.getNorthWestPoint().getX(), (int) boundsHex.getNorthWestPoint().getX() },
					new int[] { (int) boundsHex.getWestPoint().getY(), (int) armourHex.getWestPoint().getY(),
							(int) armourHex.getNorthWestPoint().getY(), (int) boundsHex.getNorthWestPoint().getY() },
					4));
		}
	}

	public void draw(Graphics2D g2d) {

		try {

			g2d.setFont(new Font("Calibri", Font.BOLD, 14));
			g2d.rotate(Math.toRadians(rotationInDegrees), center.getX(), center.getY());

			// g2d.setStroke(dashed);

			// Boundary Hex
			g2d.setColor(makeTransparent(Colours.background, 200));
			g2d.fill(boundsHex.getPoly());
			// g2d.draw(armour.getArea());
			// g2d.draw(shields.getArea());

			if (img != null) {

				g2d.drawImage(img, null, (int) center.getX() - (img.getWidth() / 2),
						(int) center.getY() - (img.getHeight() / 2));

				g2d.setColor(getColor(100));
				g2d.fill(shields);

				g2d.setColor(getColor(100));
				g2d.fill(northArmour);
				g2d.setColor(getColor(100));
				g2d.fill(northEastArmour);
				g2d.setColor(getColor(100));
				g2d.fill(southEastArmour);
				// g2d.setColor(getColor(100));
				// g2d.fill(southArmour);
				g2d.setColor(getColor(100));
				g2d.fill(southWestArmour);
				g2d.setColor(getColor(100));
				g2d.fill(northWestArmour);

				g2d.setColor(Color.BLACK);
				g2d.draw(northShields);
				g2d.draw(northEastShields);
				g2d.draw(southEastShields);
				// g2d.draw(southShields);
				g2d.draw(southWestShields);
				g2d.draw(northWestShields);
				g2d.draw(northArmour);
				g2d.draw(northEastArmour);
				g2d.draw(southEastArmour);
				// g2d.draw(southArmour);
				g2d.draw(southWestArmour);
				g2d.draw(northWestArmour);

				g2d.setColor(Colours.green);
				String hp = String.valueOf(Math.round(hull / 10));
				int stringWidth = g2d.getFontMetrics().stringWidth(hp);
				g2d.drawString(hp, (int) center.getX() - (stringWidth / 2),
						(int) shieldsHex.getSouthWestPoint().getY());

				String armourNorth = String.valueOf(Math.round((hull / 10) / 2) + " / " + String.valueOf(Math.round((hull / 10) / 3)));
				stringWidth = g2d.getFontMetrics().stringWidth(armourNorth);
				g2d.drawString(armourNorth, (int) center.getX() - (stringWidth / 2),
						(int) shieldsHex.getNorthWestPoint().getY() - 20);

				String armourNorthWest = String.valueOf(Math.round((hull / 10) / 2) + " / " + String.valueOf(Math.round((hull / 10) / 3)));
				stringWidth = g2d.getFontMetrics().stringWidth(armourNorthWest);
				g2d.drawString(armourNorthWest,
						(int) (northWestShields.getBounds2D().getCenterX() - 50) - (stringWidth / 2),
						(int) northWestShields.getBounds2D().getCenterY());

				String armourNorthEast = String.valueOf(Math.round((hull / 10) / 2) + " / " + String.valueOf(Math.round((hull / 10) / 3)));
				stringWidth = g2d.getFontMetrics().stringWidth(armourNorthEast);
				g2d.drawString(armourNorthEast,
						(int) (northEastShields.getBounds2D().getCenterX() + 50) - (stringWidth / 2),
						(int) northEastShields.getBounds2D().getCenterY());

				String armourSouthWest = String.valueOf(Math.round((hull / 10) / 2) + " / " + String.valueOf(Math.round((hull / 10) / 4)));
				stringWidth = g2d.getFontMetrics().stringWidth(armourSouthWest);
				g2d.drawString(armourSouthWest,
						(int) (southWestShields.getBounds2D().getCenterX() - 50) - (stringWidth / 2),
						(int) southWestShields.getBounds2D().getCenterY());

				String armourSouthEast = String.valueOf(Math.round((hull / 10) / 2) + " / " + String.valueOf(Math.round((hull / 10) / 4)));
				stringWidth = g2d.getFontMetrics().stringWidth(armourSouthEast);
				g2d.drawString(armourSouthEast,
						(int) (southEastShields.getBounds2D().getCenterX() + 50) - (stringWidth / 2),
						(int) southEastShields.getBounds2D().getCenterY());
			}

		} catch (Exception ex) {

			// Do Nothing

		}

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

	public void generateImage() {

		try {

			Area result = new Area(area);

			// Mirror horizontally
			result.add(ShipBuilderUtils.mirrorAlongX((int) result.getBounds2D().getMinX(), result));
			result = ShipBuilderUtils.translateToTopLeft(result);

			GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
					.getDefaultConfiguration();
			BufferedImage img = gc.createCompatibleImage((int) result.getBounds2D().getMaxX(),
					(int) result.getBounds2D().getMaxY(), BufferedImage.TYPE_INT_ARGB);

			Graphics2D gr = (Graphics2D) img.getGraphics();

			Map<?, ?> desktopHints = (Map<?, ?>) Toolkit.getDefaultToolkit()
					.getDesktopProperty("awt.font.desktophints");

			if (desktopHints != null) {
				gr.setRenderingHints(desktopHints);
			}

			gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			gr.setColor(Colours.gray);

			gr.fill(result);

			gr.setColor(makeTransparent(Colours.background, 40));
			gr.setStroke(new BasicStroke(5));
			gr.draw(result);

			gr.dispose();

			this.img = img;

			countNonAlphaPixels();

		} catch (Exception ex) {

			// Do Nothing

		}

	}

	public void add() {

		try {

			ShipBuilderUtils.add(this.area);
			generateImage();
			calculateBounds();

		} catch (Exception ex) {

			// Do Nothing

		}

	}

	public void addSpine() {

		try {

			ShipBuilderUtils.addSpine(this.area);
			generateImage();
			calculateBounds();

		} catch (Exception ex) {

			// Do Nothing

		}

	}

	public void subtract() {

		try {

			ShipBuilderUtils.subtract(this.area);
			generateImage();
			calculateBounds();

		} catch (Exception ex) {

			// Do Nothing

		}
	}

	private void countNonAlphaPixels() {
		
		hull = 0;

		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {

				if (img.getRGB(x, y) > 0) {
					hull++;
				}
			}
		}
	}
}
