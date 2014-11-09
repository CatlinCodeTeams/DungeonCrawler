package dungeon.frontend.physics;

public class Point {

	private double x;
	private double y;

	public Point(final double x, final double y) {
		this.x = x;
		this.y = y;
	}

	public double dist(final Point otherPoint) {
		final double newX = otherPoint.x - x;
		final double newY = otherPoint.y - y;

		final double sum = newX * newX + newY * newY;
		final double dist = Math.sqrt(sum);

		return dist;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(final double x) {
		this.x = x;
	}

	public void setY(final double y) {
		this.y = y;
	}

	public void moveX(final double amount) {
		x += amount;
	}

	public void moveY(final double amount) {
		y += amount;
	}

	public void move(final Vector vec) {
		x += vec.getX();
		y += vec.getY();
	}

	public Vector makeVector(final Point otherPoint) {
		final double newX = otherPoint.getX() - x;
		final double newY = otherPoint.getY() - y;

		return new Vector(newX, newY);
	}

}
