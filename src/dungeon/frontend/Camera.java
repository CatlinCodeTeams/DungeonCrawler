package dungeon.frontend;

import dungeon.frontend.physics.Point;
import dungeon.frontend.physics.Vector;

public class Camera {

	Vector velocity;
	Point location;
	
	public Camera(Point target, int x, int y){
		location = new Point(x,y);
		velocity = new Vector(0,0);
	}
	
	public void update(Point target){
		Vector vec = location.makeVector(target);
		vec.setLength(2);
		velocity.add(vec);
		
		velocity.limit(6);
	}
	
	
	public Point getLocation(){
		return location;
	}
	
	public double getX(){
		return location.getX();
	}
	
	public double getY(){
		return location.getY();
	}
	
}
