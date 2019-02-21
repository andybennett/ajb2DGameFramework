package ajb.examples.helpers;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Vessel {

	public BufferedImage img = null;
	public Area area = null;
	public Area bounds = null;
	public Point2D.Double center = null;

	public boolean selected = false;

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

			bounds = new Area(shieldsHex.getPoly());

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

			if (img != null) {

				if (selected) {

					// Boundary Hex
					g2d.setColor(Colours.makeTransparent(Colours.background, 200));
					g2d.fill(boundsHex.getPoly());
					
					g2d.setColor(Colours.gray.darker());
					g2d.setStroke(new BasicStroke(2));
					g2d.draw(boundsHex.getPoly());

//					g2d.setColor(Colours.getColor(30));
//					g2d.fill(shields);
//
//					g2d.setColor(Colours.getColor(100));
//					g2d.fill(northArmour);
//					g2d.setColor(Colours.getColor(100));
//					g2d.fill(northEastArmour);
//					g2d.setColor(Colours.getColor(100));
//					g2d.fill(southEastArmour);
//					// g2d.setColor(getColor(100));
//					// g2d.fill(southArmour);
//					g2d.setColor(Colours.getColor(100));
//					g2d.fill(southWestArmour);
//					g2d.setColor(Colours.getColor(100));
//					g2d.fill(northWestArmour);
//
//					g2d.setColor(Color.BLACK);
//					g2d.draw(northShields);
//					g2d.draw(northEastShields);
//					g2d.draw(southEastShields);
//					// g2d.draw(southShields);
//					g2d.draw(southWestShields);
//					g2d.draw(northWestShields);
//					g2d.draw(northArmour);
//					g2d.draw(northEastArmour);
//					g2d.draw(southEastArmour);
//					// g2d.draw(southArmour);
//					g2d.draw(southWestArmour);
//					g2d.draw(northWestArmour);
//
//					g2d.setColor(Colours.green);
//					String hp = String.valueOf(Math.round(hull / 10));
//					int stringWidth = g2d.getFontMetrics().stringWidth(hp);
//					g2d.drawString(hp, (int) center.getX() - (stringWidth / 2),
//							(int) shieldsHex.getSouthWestPoint().getY());
//
//					String armourNorth = String
//							.valueOf(Math.round((hull / 10) / 2) + " / " + String.valueOf(Math.round((hull / 10) / 3)));
//					stringWidth = g2d.getFontMetrics().stringWidth(armourNorth);
//					g2d.drawString(armourNorth, (int) center.getX() - (stringWidth / 2),
//							(int) shieldsHex.getNorthWestPoint().getY() - 20);
//
//					String armourNorthWest = String
//							.valueOf(Math.round((hull / 10) / 2) + " / " + String.valueOf(Math.round((hull / 10) / 3)));
//					stringWidth = g2d.getFontMetrics().stringWidth(armourNorthWest);
//					g2d.drawString(armourNorthWest,
//							(int) (northWestShields.getBounds2D().getCenterX() - 50) - (stringWidth / 2),
//							(int) northWestShields.getBounds2D().getCenterY());
//
//					String armourNorthEast = String
//							.valueOf(Math.round((hull / 10) / 2) + " / " + String.valueOf(Math.round((hull / 10) / 3)));
//					stringWidth = g2d.getFontMetrics().stringWidth(armourNorthEast);
//					g2d.drawString(armourNorthEast,
//							(int) (northEastShields.getBounds2D().getCenterX() + 50) - (stringWidth / 2),
//							(int) northEastShields.getBounds2D().getCenterY());
//
//					String armourSouthWest = String
//							.valueOf(Math.round((hull / 10) / 2) + " / " + String.valueOf(Math.round((hull / 10) / 4)));
//					stringWidth = g2d.getFontMetrics().stringWidth(armourSouthWest);
//					g2d.drawString(armourSouthWest,
//							(int) (southWestShields.getBounds2D().getCenterX() - 50) - (stringWidth / 2),
//							(int) southWestShields.getBounds2D().getCenterY());
//
//					String armourSouthEast = String
//							.valueOf(Math.round((hull / 10) / 2) + " / " + String.valueOf(Math.round((hull / 10) / 4)));
//					stringWidth = g2d.getFontMetrics().stringWidth(armourSouthEast);
//					g2d.drawString(armourSouthEast,
//							(int) (southEastShields.getBounds2D().getCenterX() + 50) - (stringWidth / 2),
//							(int) southEastShields.getBounds2D().getCenterY());
				}

				g2d.setColor(Colours.makeTransparent(Colours.background, 200));
				g2d.fillRect((int) center.getX() - (img.getWidth() / 2), (int) center.getY() - (img.getHeight() / 2),
						img.getWidth(), img.getHeight());

				g2d.drawImage(img, null, (int) center.getX() - (img.getWidth() / 2),
						(int) center.getY() - (img.getHeight() / 2));

			}

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}

	public void generateImage() {

		try {

			// Copy the area.
			Area result = new Area(area);

			// Mirror horizontally.
			result.add(AreaUtils.mirrorAlongX((int) result.getBounds2D().getMinX(), result));
			
			// Translate to top left.
			result = AreaUtils.translateToTopLeft(result);

			// Create image.
			BufferedImage img = ImageUtils.create(result.getBounds2D().getMaxX(), result.getBounds2D().getMaxY());

			// Get the graphics from the image to write to.
			Graphics2D gr = (Graphics2D) img.getGraphics();

			// Set colour.
			gr.setColor(Colours.gray);

			// Draw.
			gr.fill(result);

			// Draw Lines.
			gr.setColor(Colours.makeTransparent(Colours.background, 60));
			gr.setStroke(new BasicStroke(5));
			gr.draw(result);

			// Dispose the graphics.
			gr.dispose();

			// Set the image.
			this.img = img;

			// Set the hull count.
			hull = ImageUtils.countNonAlphaPixels(img);

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}

	public void random() {

		try {

			this.area = VesselUtils.generate();
			generateImage();
			calculateBounds();

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}

	public void add() {

		try {

			AreaUtils.addRandomBlock(this.area);
			generateImage();
			calculateBounds();

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}

	public void addSpine() {

		try {

			AreaUtils.addRandomBlockAlongMinX(this.area);
			generateImage();
			calculateBounds();

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}

	public void subtract() {

		try {

			AreaUtils.subtractRandomBlock(this.area);
			generateImage();
			calculateBounds();

		} catch (Exception ex) {

			ex.printStackTrace();

		}
	}

	public void flip() {

		try {

			this.area = AreaUtils.flipVertically(this.area);
			generateImage();
			calculateBounds();

		} catch (Exception ex) {

			ex.printStackTrace();

		}
	}

	public boolean containsPoint(Point2D.Double point) {

		boolean result = false;

		if (bounds.contains(point)) {

			result = true;

		}

		return result;

	}
}
