package dungeon.frontend.physics;


public class Vector {

	private double x;
	private double y;
	
	public Vector(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
	
	public void setX(double x){
		this.x = x;
	}
	public void setY(double y){
		this.y = y;
	}
	
	public void addX(double d){
		this.x += d;
	}
	
	public void addY(double d){
		this.y += d;
	}
	
	public void multiply(double factor){
		this.x *= factor;
		this.y *= factor;
	}
	
	public void divide(double factor){
		this.x /= factor;
		this.y /= factor;
	}
	
	public void add(Vector vec){
		this.x += vec.getX();
		this.y += vec.getY();
	}
	
	public void subtract(Vector vec){
		this.x -= vec.getX();
		this.y -= vec.getY();
	}
	
	public double getLength(){
		double sum = (x*x)+(y*y);
		return Math.sqrt(sum);
	}
	
	public void normalize(){
		double length = getLength();
		
		if (length !=0){
			x /= length;
			y /= length;
		}
	}
	
	public void limit(double limit){
		if (getLength() > limit){
			setLength(limit);
		}
	}
	
	public void setLength(double length){
		normalize();
		multiply(length);
	}
}
