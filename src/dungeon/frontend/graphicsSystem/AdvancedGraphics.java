package dungeon.frontend.graphicsSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;

import dungeon.frontend.physics.Point;
import dungeon.frontend.physics.Vector;

public class AdvancedGraphics {

	Graphics myPen;

	int camX;
	int camY;

	public Graphics getBasicPen() {
		return myPen;
	}

	public AdvancedGraphics(final Graphics pen) {

		System.out.println("New AdvancedGraphics object created.");
		myPen = pen;

		camX = 0;
		camY = 0;
	}

	// --------------------------------------

	public void setBasicGraphics(final Graphics pen) {
		myPen = pen;
	}

	// -----------------------------------------
	// Note: The position that the camera is set to represents the top-right
	// corner of
	// the screen. A camera position of (0,0) indicates that the top right
	// corner of the view lines up
	// with the points (0,0).

	public int getCamX() {
		return camX;
	}

	public int getCamY() {
		return camY;
	}

	public void moveCameraPosition(final int x, final int y) {
		// resetCameraPosition();

		camX = x;
		camY = y;

		translate(-1 * camX, -1 * camY);

	}

	// Given (x,y) coordinate, an angle, a number of sides, and a radius, an
	// ArrayList<Point> is generated that
	// contains all the points of the regular polygon with the attributes
	// specified in the parameters.
	private ArrayList<Point> getRegularPolygonList(final int x, final int y,
			final int angle, final int sides, final int size) {

		final ArrayList<Point> pointList = new ArrayList<Point>();
		final ArrayList<Vector> vectorList = new ArrayList<Vector>();

		for (int s = 0; s < sides; s++) {
			pointList.add(new Point(0, 0));
			vectorList.add(new Vector(0, 0));
		}

		for (int s = 0; s < sides; s++) {
			pointList.set(s, new Point(x, y));

			final double tempAngleDegrees = angle + 360.0 / sides * s;
			final double tempAngleRad = Math.toRadians(tempAngleDegrees);

			final double newX = size / 2.0 * Math.cos(tempAngleRad);
			final double newY = size / 2.0 * Math.sin(tempAngleRad);

			vectorList.set(s, new Vector(newX, newY));

			pointList.get(s).move(vectorList.get(s));
		}
		return pointList;
	}

	// Given (x,y) coordinate, an angle, a number of "points", and a radius, an
	// ArrayList<Point> is generated that
	// contains all the points of the starred polygon with the attributes
	// specified in the parameters.
	public ArrayList<Point> getStarredPolygonList(final int x, final int y,
			final int angle, final int nPoints, final int size) {

		final ArrayList<Point> pointyPoints = new ArrayList<Point>();
		final ArrayList<Point> concavePoints = new ArrayList<Point>();

		final ArrayList<Point> allPoints = new ArrayList<Point>();

		final ArrayList<Vector> pointyVectors = new ArrayList<Vector>();
		final ArrayList<Vector> concaveVectors = new ArrayList<Vector>();

		for (int s = 0; s < nPoints; s++) {
			pointyPoints.add(new Point(0, 0));
			concavePoints.add(new Point(0, 0));
			pointyVectors.add(new Vector(0, 0));
			concaveVectors.add(new Vector(0, 0));
		}

		for (int s = 0; s < nPoints; s++) {
			pointyPoints.set(s, new Point(x, y));
			final double tempAngleDegrees = angle + 360.0 / nPoints * s;
			final double tempAngleRad = Math.toRadians(tempAngleDegrees);

			final double newX = size / 2.0 * Math.cos(tempAngleRad);
			final double newY = size / 2.0 * Math.sin(tempAngleRad);

			pointyVectors.set(s, new Vector(newX, newY));
			pointyPoints.get(s).move(pointyVectors.get(s));
		}

		for (int s = 0; s < nPoints; s++) {
			concavePoints.set(s, new Point(x, y));
			final double tempAngleDegrees = 180.0 / nPoints + angle + 360.0
					/ nPoints * s;
			final double tempAngleRad = Math.toRadians(tempAngleDegrees);

			final double newX = size / 4.0 * Math.cos(tempAngleRad);
			final double newY = size / 4.0 * Math.sin(tempAngleRad);

			concaveVectors.set(s, new Vector(newX, newY));
			concavePoints.get(s).move(concaveVectors.get(s));
		}

		for (int s = 0; s < nPoints; s++) {
			allPoints.add(pointyPoints.get(s));
			allPoints.add(concavePoints.get(s));
		}

		return allPoints;
	}

	// Redirects to the default java Graphics drawPolygon method
	public void drawPolygon(final int[] xPoints, final int[] yPoints,
			final int nPoints) {
		myPen.drawPolygon(xPoints, yPoints, nPoints);

	}

	// Draws a regular polygon based on the given parameters
	public void drawRegularPolygon(final int x, final int y, final int angle,
			final int sides, final int size) {
		final ArrayList<Point> pointList = getRegularPolygonList(x, y, angle,
				sides, size);
		final int[] xPoints = new int[pointList.size()];
		final int[] yPoints = new int[pointList.size()];

		for (int i = 0; i < pointList.size(); i++) {
			xPoints[i] = (int) pointList.get(i).getX();
			yPoints[i] = (int) pointList.get(i).getY();
		}

		final int nPoints = pointList.size();

		myPen.drawPolygon(xPoints, yPoints, nPoints);

	}

	// draws a starred polygon based on the given parameters
	public void drawStarredPolygon(final int x, final int y, final int angle,
			final int nPoints, final int size) {
		final ArrayList<Point> pointList = getStarredPolygonList(x, y, angle,
				nPoints, size);
		final int[] xPoints = new int[pointList.size()];
		final int[] yPoints = new int[pointList.size()];

		for (int i = 0; i < pointList.size(); i++) {
			xPoints[i] = (int) pointList.get(i).getX();
			yPoints[i] = (int) pointList.get(i).getY();
		}

		final int listSize = pointList.size();

		myPen.drawPolygon(xPoints, yPoints, listSize);
	}

	public void fillStarredPolygon(final int x, final int y, final int angle,
			final int nPoints, final int size) {
		final ArrayList<Point> pointList = getStarredPolygonList(x, y, angle,
				nPoints, size);
		final int[] xPoints = new int[pointList.size()];
		final int[] yPoints = new int[pointList.size()];

		for (int i = 0; i < pointList.size(); i++) {
			xPoints[i] = (int) pointList.get(i).getX();
			yPoints[i] = (int) pointList.get(i).getY();
		}

		final int listSize = pointList.size();

		myPen.fillPolygon(xPoints, yPoints, listSize);
	}

	// The following are just drawPolygon, except with specified values for the
	// number of sides parameter.
	public void drawTriangle(final int x, final int y, final int angle,
			final int size) {
		drawRegularPolygon(x, y, angle, 3, size);
	}

	public void drawSquare(final int x, final int y, final int angle,
			final int size) {
		drawRegularPolygon(x, y, angle, 4, size);
	}

	public void drawPentagon(final int x, final int y, final int angle,
			final int size) {
		drawRegularPolygon(x, y, angle, 5, size);
	}

	public void drawHexagon(final int x, final int y, final int angle,
			final int size) {
		drawRegularPolygon(x, y, angle, 6, size);
	}

	public void drawSeptagon(final int x, final int y, final int angle,
			final int size) {
		drawRegularPolygon(x, y, angle, 7, size);
	}

	public void drawOctagon(final int x, final int y, final int angle,
			final int size) {
		drawRegularPolygon(x, y, angle, 8, size);
	}

	// Fills a regular polygon based on the given paramters.
	public void fillRegularPolygon(final int x, final int y, final int angle,
			final int sides, final int size) {
		final ArrayList<Point> pointList = getRegularPolygonList(x, y, angle,
				sides, size);
		final int[] xPoints = new int[pointList.size()];
		final int[] yPoints = new int[pointList.size()];

		for (int i = 0; i < pointList.size(); i++) {
			xPoints[i] = (int) pointList.get(i).getX();
			yPoints[i] = (int) pointList.get(i).getY();
		}

		final int nPoints = pointList.size();

		myPen.fillPolygon(xPoints, yPoints, nPoints);
	}

	// The following are just redirects to fillPolygon, but with sepcified
	// numbers of sides.
	public void fillTriangle(final int x, final int y, final int angle,
			final int size) {
		fillRegularPolygon(x, y, angle, 3, size);
	}

	public void fillSquare(final int x, final int y, final int angle,
			final int size) {
		fillRegularPolygon(x, y, angle, 4, size);
	}

	public void fillPentagon(final int x, final int y, final int angle,
			final int size) {
		fillRegularPolygon(x, y, angle, 5, size);
	}

	public void fillHexagon(final int x, final int y, final int angle,
			final int size) {
		fillRegularPolygon(x, y, angle, 6, size);
	}

	public void fillSeptagon(final int x, final int y, final int angle,
			final int size) {
		fillRegularPolygon(x, y, angle, 7, size);
	}

	public void fillOctagon(final int x, final int y, final int angle,
			final int size) {
		fillRegularPolygon(x, y, angle, 8, size);
	}

	// ------------------------------------------------

	public void clearRect(final int x, final int y, final int width,
			final int height) {
		myPen.clearRect(x, y, width, height);

	}

	public void clipRect(final int x, final int y, final int width,
			final int height) {
		myPen.clipRect(x, y, width, height);

	}

	public void copyArea(final int x, final int y, final int width,
			final int height, final int dx, final int dy) {
		myPen.copyArea(x, y, width, height, dx, dy);

	}

	public Graphics create() {
		myPen.create();
		return null;
	}

	public void dispose() {
		myPen.dispose();

	}

	public void drawArc(final int x, final int y, final int width,
			final int height, final int startAngle, final int arcAngle) {
		myPen.drawArc(x, y, width, height, startAngle, arcAngle);

	}

	public boolean drawImage(final Image img, final int x, final int y,
			final ImageObserver observer) {
		myPen.drawImage(img, x, y, observer);
		return false;
	}

	public boolean drawImage(final Image img, final int x, final int y,
			final Color bgcolor, final ImageObserver observer) {
		myPen.drawImage(img, x, y, bgcolor, observer);
		return false;
	}

	public boolean drawImage(final Image img, final int x, final int y,
			final int width, final int height, final ImageObserver observer) {
		myPen.drawImage(img, x, y, width, height, observer);
		return false;
	}

	public boolean drawImage(final Image img, final int x, final int y,
			final int width, final int height) {
		drawImage(img, x, y, width, height, null);
		return false;
	}

	public void drawImage(final Image img, final Point point, final int width,
			final int height) {
		drawImage(img, (int) point.getX(), (int) point.getY(), width, height);
	}

	public boolean drawImage(final Image img, final int x, final int y,
			final int width, final int height, final Color bgcolor,
			final ImageObserver observer) {
		myPen.drawImage(img, x, y, width, height, bgcolor, observer);
		return false;
	}

	public boolean drawImage(final Image img, final int dx1, final int dy1,
			final int dx2, final int dy2, final int sx1, final int sy1,
			final int sx2, final int sy2, final ImageObserver observer) {
		myPen.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
		return false;
	}

	public boolean drawImage(final Image img, final int dx1, final int dy1,
			final int dx2, final int dy2, final int sx1, final int sy1,
			final int sx2, final int sy2, final Color bgcolor,
			final ImageObserver observer) {
		myPen.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor,
				observer);
		return false;
	}

	public void drawMirroredImage(Image img, final Point point,
			final int width, final int height) {

		// Flip the image horizontally
		final AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-img.getWidth(null), 0);
		final AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		img = op.filter((BufferedImage) img, null);

		drawImage(img, (int) point.getX(), (int) point.getY(), width, height,
				null);

	}

	public void drawRotatedImage(final Image image, final Point point,
			final int width, final int height, final int rotation) {

		final double rotationRequired = Math.toRadians(rotation);
		final double locationX = image.getWidth(null) / 2;
		final double locationY = image.getHeight(null) / 2;
		final AffineTransform tx = AffineTransform.getRotateInstance(
				rotationRequired, locationX, locationY);
		final AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_BILINEAR);

		drawImage(op.filter((BufferedImage) image, null), point, width, height);
	}

	public void drawLine(final int x1, final int y1, final int x2, final int y2) {
		myPen.drawLine(x1, y1, x2, y2);

	}

	public void drawOval(final int x, final int y, final int width,
			final int height) {
		myPen.drawOval(x, y, width, height);

	}

	public void drawCircle(final int x, final int y, final int radius) {
		myPen.drawOval(x, y, radius, radius);
	}

	public void drawCenteredCircle(final int x, final int y, final int radius) {
		drawCircle(x - radius / 2, y - radius / 2, radius);
	}

	public void fillCircle(final int x, final int y, final int radius) {
		myPen.fillOval(x, y, radius, radius);
	}

	public void drawPolyline(final int[] xPoints, final int[] yPoints,
			final int nPoints) {
		myPen.drawPolyline(xPoints, yPoints, nPoints);

	}

	public void drawRoundRect(final int x, final int y, final int width,
			final int height, final int arcWidth, final int arcHeight) {
		myPen.drawRoundRect(x, y, width, height, arcWidth, arcHeight);

	}

	public void drawRect(final int x, final int y, final int width,
			final int height) {
		myPen.drawRect(x, y, width, height);
	}

	public void drawString(final String str, final int x, final int y) {
		myPen.drawString(str, x, y);

	}

	public void drawString(final AttributedCharacterIterator iterator,
			final int x, final int y) {
		myPen.drawString(iterator, x, y);

	}

	public void fillArc(final int x, final int y, final int width,
			final int height, final int startAngle, final int arcAngle) {
		myPen.fillArc(x, y, width, height, startAngle, arcAngle);

	}

	public void fillOval(final int x, final int y, final int width,
			final int height) {
		myPen.fillOval(x, y, width, height);

	}

	public void fillPolygon(final int[] xPoints, final int[] yPoints,
			final int nPoints) {
		myPen.fillPolygon(xPoints, yPoints, nPoints);

	}

	public void fillRect(final int x, final int y, final int width,
			final int height) {
		myPen.fillRect(x, y, width, height);

	}

	public void fillRoundRect(final int x, final int y, final int width,
			final int height, final int arcWidth, final int arcHeight) {
		myPen.fillRoundRect(x, y, width, height, arcWidth, arcHeight);

	}

	public Shape getClip() {
		myPen.getClip();
		return null;
	}

	public Rectangle getClipBounds() {
		myPen.getClipBounds();
		return null;
	}

	public Color getColor() {
		myPen.getColor();
		return null;
	}

	public Font getFont() {
		myPen.getFont();
		return null;
	}

	public FontMetrics getFontMetrics(final Font f) {
		myPen.getFontMetrics(f);
		return null;
	}

	public void setClip(final Shape clip) {
		myPen.setClip(clip);

	}

	public void setClip(final int x, final int y, final int width,
			final int height) {
		myPen.setClip(x, y, width, height);

	}

	public void setColor(final Color c) {
		myPen.setColor(c);

	}

	public void setFont(final Font font) {
		myPen.setFont(font);

	}

	public void setPaintMode() {
		myPen.setPaintMode();

	}

	public void setXORMode(final Color c1) {
		myPen.setXORMode(c1);

	}

	public void translate(final int x, final int y) {
		myPen.translate(x, y);

	}

}
