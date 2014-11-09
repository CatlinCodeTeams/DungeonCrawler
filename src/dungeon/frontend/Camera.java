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
		vec.setLength(1);
		velocity.add(vec);
		velocity.limit(3);
		
		if (location.dist(target)<3){
			velocity = new Vector(0,0);
		}
		
		location.move(velocity);
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
