package ajb.examples.helpers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.UUID;

import ajb.ships.ShipUtils;

public class Vessel implements Serializable {

	private static final long serialVersionUID = 6238354968702292635L;

	public transient Area halfArea = null;
	public transient Area displayArea = null;
	public transient Area bounds = null;

	public String identifier = UUID.randomUUID().toString();
	public String name = null;
	public Point2D.Double center = null;
	public boolean selected = false;
	double rotationInDegrees = 0;
	double shields = 0;
	double armour = 0;
	double hull = 0;
	int[][] pixelArray = null;
	Color color = Colours.gray.darker();

	public Vessel(Area area, Point2D.Double center) {

		this.halfArea = area;
		this.center = center;

		generateDisplayArea();

	}

	public void drawBeforeTransform(Graphics2D g2d, int width, int height) {

//		if (selected) {
//
//			g2d.setFont(new Font("Calibri", Font.BOLD, 18));
//
//			g2d.setColor(Colours.makeTransparent(Colours.background, 200));
//
//			g2d.fill(new Rectangle2D.Double(width - 400, 0, width, height));
//
//			g2d.setColor(Colours.green);
//			g2d.drawString(identifier, width - (g2d.getFontMetrics().stringWidth(identifier) + 20), 20);
//
//		}

	}

	public void draw(Graphics2D g2d) {

		try {

			if (halfArea == null) {

				generateDisplayArea();

			}

			g2d.rotate(Math.toRadians(rotationInDegrees), center.getX(), center.getY());

			g2d.setColor(Colours.makeTransparent(Colours.background, 200));
			g2d.fill(displayArea.getBounds2D());

			g2d.setColor(color);
			g2d.fill(displayArea);

		} catch (Exception ex) {

			ex.printStackTrace();

		}
	}

	public void generateDisplayArea() {

		try {

			if (halfArea == null) {

				halfArea = new Area();

				// Build the area from the pixel array
				for (int x = 0; x < pixelArray.length; x++) {

					for (int y = 0; y < pixelArray[0].length; y++) {

						if (pixelArray[x][y] == 1) {
							halfArea.add(new Area(new Rectangle2D.Double(x, y, 1, 1)));
						}

					}

				}

			}

			// Copy the area.
			displayArea = new Area(halfArea);

			// Mirror horizontally.
			displayArea.add(AreaUtils.mirrorAlongX((int) displayArea.getBounds2D().getMinX(), displayArea));
			displayArea = AreaUtils.translateToTopLeft(displayArea);

			// Translate to center point.
			displayArea = AreaUtils.translateToPoint(displayArea,
					new Point2D.Double(center.getX() - (displayArea.getBounds2D().getWidth() / 2),
							center.getY() - (displayArea.getBounds2D().getHeight() / 2)));

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}

	public void random() {

		try {

			this.halfArea = ShipUtils.generate();
			generateDisplayArea();

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}

	public void add() {

		try {

			AreaUtils.addRandomBlock(this.halfArea);
			generateDisplayArea();

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}

	public void addSpine() {

		try {

			AreaUtils.addRandomBlockAlongMinX(this.halfArea);
			generateDisplayArea();

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}

	public void subtract() {

		try {

			AreaUtils.subtractRandomBlock(this.halfArea);
			generateDisplayArea();

		} catch (Exception ex) {

			ex.printStackTrace();

		}
	}

	public void subtractRandomLine() {

		try {

			AreaUtils.subtractRandomLine(this.halfArea);
			generateDisplayArea();

		} catch (Exception ex) {

			ex.printStackTrace();

		}
	}

	public void flip() {

		try {

			this.halfArea = AreaUtils.flipVertically(this.halfArea);
			generateDisplayArea();

		} catch (Exception ex) {

			ex.printStackTrace();

		}
	}

	public boolean containsPoint(Point2D.Double point) {

		boolean result = false;

		if (displayArea.getBounds2D().contains(point)) {

			result = true;

		}

		return result;

	}

	public void save() {

		// Copy the area.
		Area copy = new Area(halfArea);
		copy = AreaUtils.translateToTopLeft(copy);

		pixelArray = new int[(int) copy.getBounds2D().getWidth()][(int) copy.getBounds2D().getHeight()];

		for (int x = 0; x < (int) copy.getBounds2D().getWidth(); x++) {

			for (int y = 0; y < (int) copy.getBounds2D().getHeight(); y++) {

				pixelArray[x][y] = copy.contains(x, y) ? 1 : 0;

			}

		}
		
		try {

			FileOutputStream fos = new FileOutputStream(getIdentifier() + ".vessel");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}

	public void move(Point2D.Double newCenter) {

		this.center = newCenter;
		generateDisplayArea();

	}

	public String getIdentifier() {

		String result = identifier;

		if (name != null) {

			result = name;

		}

		return result;

	}
}
