package ajb.examples.helpers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.UUID;

public class Asteroid implements Serializable {

	private static final long serialVersionUID = 4674265520763243820L;
	
	public transient Area area = null;
	public transient Area bounds = null;

	public String identifier = UUID.randomUUID().toString();
	public Point2D.Double center = null;
	double rotationInDegrees = 0;
	Color color = Colours.brown;

	public Asteroid(Area area, Point2D.Double center) {

		this.area = area;
		this.area = AreaUtils.translateToTopLeft(area);
		this.area = AreaUtils.translateToPoint(area,
				new Point2D.Double(center.getX() - (area.getBounds2D().getWidth() / 2),
						center.getY() - (area.getBounds2D().getHeight() / 2)));		
		this.center = center;

	}

	public void draw(Graphics2D g2d) {

		try {

			g2d.rotate(Math.toRadians(rotationInDegrees), center.getX(), center.getY());

			g2d.setColor(color);
			g2d.fill(area);

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}

	public boolean containsPoint(Point2D.Double point) {

		boolean result = false;

		if (area.contains(point)) {

			result = true;

		}

		return result;

	}

	public void move(Point2D.Double newCenter) {
		
		this.center = newCenter;

	}
}
