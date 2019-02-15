package ajb.examples.helpers;

import java.awt.Polygon;
import java.awt.geom.Point2D;

public class Hex {

	private String identifier;
	private int size = 100;
	private Point2D.Double middlePoint;
	private Point2D.Double[] points;
	private Polygon poly;

	private Point2D.Double northWestPoint = null;
	private Point2D.Double northEastPoint = null;
	private Point2D.Double eastPoint = null;
	private Point2D.Double southEastPoint = null;
	private Point2D.Double southWestPoint = null;
	private Point2D.Double westPoint = null;

	/**
	 * Constructor
	 * 
	 * @param point
	 *            {@link Point2D.Double}
	 * @param identifier
	 *            {@link String}
	 */
	public Hex(Point2D.Double point, String identifier, int size) {

		this.size = size;
		this.identifier = identifier;
		this.middlePoint = point;

		double h = calculateH(size);
		double r = calculateR(size);

		double x = (point.getX() - (size / 2));
		double y = (point.getY() - r);

		this.northWestPoint = new Point2D.Double(x, y);
		this.northEastPoint = new Point2D.Double(x + size, y);
		this.eastPoint = new Point2D.Double(x + size + h, y + r);
		this.southEastPoint = new Point2D.Double(x + size, y + r + r);
		this.southWestPoint = new Point2D.Double(x, y + r + r);
		this.westPoint = new Point2D.Double(x - h, y + r);

		int[] xPoints = new int[] { (int) this.northWestPoint.getX(), (int) this.northEastPoint.getX(),
				(int) this.eastPoint.getX(), (int) this.southEastPoint.getX(), (int) this.southWestPoint.getX(),
				(int) this.westPoint.getX() };
		int[] yPoints = new int[] { (int) this.northWestPoint.getY(), (int) this.northEastPoint.getY(),
				(int) this.eastPoint.getY(), (int) this.southEastPoint.getY(), (int) this.southWestPoint.getY(),
				(int) this.westPoint.getY() };

		this.poly = new Polygon(xPoints, yPoints, 6);
	}

	/**
	 * Returns true if the passed in point is within this hex
	 * 
	 * @param point
	 *            {@link Point2D.Double}
	 * @return {@link boolean}
	 */
	public boolean isPointInHex(Point2D.Double point) {

		int j = points.length - 1;
		boolean oddNodes = false;

		for (int i = 0; i < points.length; i++) {
			if (points[i].getY() < point.getY() && points[j].getY() >= point.getY()
					|| points[j].getY() < point.getY() && points[i].getY() >= point.getY()) {
				if (points[i].getX() + (point.getY() - points[i].getY()) / (points[j].getY() - points[i].getY())
						* (points[j].getX() - points[i].getX()) < point.getX()) {
					oddNodes = !oddNodes;
				}
			}
			j = i;
		}

		return oddNodes;
	}

	// Split these 3 functions into separate util class?
	private float calculateH(float side) {
		return (float) (Math.sin(degreesToRadians(30)) * side);
	}

	private float calculateR(float side) {
		return (float) (Math.cos(degreesToRadians(30)) * side);
	}

	private double degreesToRadians(double degrees) {
		return degrees * Math.PI / 180;
	}

	// Getter and Setters
	public float getHeight() {
		float r = calculateR(size);
		return r + r;
	}

	public float getWidth() {
		float h = calculateH(size);
		return size + h;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Point2D.Double getMiddlePoint() {
		return middlePoint;
	}

	public void setMiddlePoint(Point2D.Double middlePoint) {
		this.middlePoint = middlePoint;
	}

	public Point2D.Double[] getPoints() {
		return points;
	}

	public void setPoints(Point2D.Double[] points) {
		this.points = points;
	}

	public Polygon getPoly() {
		return poly;
	}

	public void setPoly(Polygon poly) {
		this.poly = poly;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Point2D.Double getNorthWestPoint() {
		return northWestPoint;
	}

	public void setNorthWestPoint(Point2D.Double northWestPoint) {
		this.northWestPoint = northWestPoint;
	}

	public Point2D.Double getNorthEastPoint() {
		return northEastPoint;
	}

	public void setNorthEastPoint(Point2D.Double northEastPoint) {
		this.northEastPoint = northEastPoint;
	}

	public Point2D.Double getSouthEastPoint() {
		return southEastPoint;
	}

	public void setSouthEastPoint(Point2D.Double southEastPoint) {
		this.southEastPoint = southEastPoint;
	}

	public Point2D.Double getSouthWestPoint() {
		return southWestPoint;
	}

	public void setSouthWestPoint(Point2D.Double southWestPoint) {
		this.southWestPoint = southWestPoint;
	}

	public Point2D.Double getWestPoint() {
		return westPoint;
	}

	public void setWestPoint(Point2D.Double westPoint) {
		this.westPoint = westPoint;
	}

	public Point2D.Double getEastPoint() {
		return eastPoint;
	}

	public void setEastPoint(Point2D.Double eastPoint) {
		this.eastPoint = eastPoint;
	}
}
