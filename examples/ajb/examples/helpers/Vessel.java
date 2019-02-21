package ajb.examples.helpers;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
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

	double rotationInDegrees = 0;

	double shields = 0;
	double armour = 0;
	double hull = 0;

	public Vessel(Area area, Point2D.Double center) {

		this.area = area;
		this.center = center;

		generateImage();
		calculateBounds();
		calculateStats();

	}

	public void calculateStats() {

		// Set the hull count.
		hull = ImageUtils.countNonAlphaPixels(img);	
		
		armour = Math.round(hull / 4);
		shields = Math.round(hull / 3);
		
	}
	
	public void calculateBounds() {

		if (this.img != null) {

			int size = img.getHeight() > img.getWidth() ? img.getHeight() : img.getWidth();

			boundsHex = new Hex(this.center, "", size);

			bounds = new Area(boundsHex.getPoly());

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

			// Dispose the graphics.
			gr.dispose();

			// Set the image.
			this.img = img;

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}

	public void random() {

		try {

			this.area = VesselUtils.generate();
			generateImage();
			calculateBounds();
			calculateStats();

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}

	public void add() {

		try {

			AreaUtils.addRandomBlock(this.area);
			generateImage();
			calculateBounds();
			calculateStats();

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}

	public void addSpine() {

		try {

			AreaUtils.addRandomBlockAlongMinX(this.area);
			generateImage();
			calculateBounds();
			calculateStats();

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}

	public void subtract() {

		try {

			AreaUtils.subtractRandomBlock(this.area);
			generateImage();
			calculateBounds();
			calculateStats();

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
