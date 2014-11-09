package dungeon.frontend.physics;

public class Vector {

	private double x;
	private double y;

	public Vector(final double x, final double y) {
		this.x = x;
		this.y = y;
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

	public void addX(final double d) {
		x += d;
	}

	public void addY(final double d) {
		y += d;
	}

	public void multiply(final double factor) {
		x *= factor;
		y *= factor;
	}

	public void divide(final double factor) {
		x /= factor;
		y /= factor;
	}

	public void add(final Vector vec) {
		x += vec.getX();
		y += vec.getY();
	}

	public void subtract(final Vector vec) {
		x -= vec.getX();
		y -= vec.getY();
	}

	public double getLength() {
		final double sum = x * x + y * y;
		return Math.sqrt(sum);
	}

	public void normalize() {
		final double length = getLength();

		if (length != 0) {
			x /= length;
			y /= length;
		}
	}

	public void limit(final double limit) {
		if (getLength() > limit) {
			setLength(limit);
		}
	}

	public void setLength(final double length) {
		normalize();
		multiply(length);
	}
}
