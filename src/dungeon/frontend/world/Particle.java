package dungeon.frontend.world;




import java.awt.Color;
import java.util.Random;

import dungeon.frontend.graphicsSystem.AdvancedGraphics;
import dungeon.util.physics.Point;
import dungeon.util.physics.Vector;

public abstract class Particle {

	Random rand = new Random();

	Point location;
	Vector speed;
	Color color;
	double radius;
	int timer;
	
	Vector gravity = new Vector(0, .1);

	boolean finished;

	public Particle(int x, int y, Color color, int size, int velocity) {

		init(x,y,color,size,velocity);
	}
	
	public Particle(Point location, Color color, int size, int velocity){
		init((int)location.getX(), (int)location.getY(), color, size, velocity);
	}

	
	//Use only with the constructors.
	public void init(int x, int y, Color color, int size, int velocity){
		
		finished = false;

		location = new Point(x, y);
		speed = new Vector(0, 0);

		speed.setX(velocity * 2 * (rand.nextDouble() - .5));
		speed.setY(velocity * 2 * (rand.nextDouble() - .5));

		if (speed.getLength() == 0) {
			speed = new Vector(1, 1);
		}

		this.color = color;

		if (size >= 2) {
			final double a = (-(size / 2)) + rand.nextInt(2 * size);
			radius = size + (int) a;
		} else {
			radius = size;
		}

		timer = 0;
	}
	
	public boolean isFinished(){
		return finished;
	}
	
	public void update(World world) {

		timer++;
		location.move(speed);

		radius = (radius) /= ((1.0) + (timer / 10000.0));

		
		speed.add(gravity);

		if ((timer == 100))
			finished = true;
		
		if (outOfBounds(320, 240)){
			finished = true;
		}
	}
	
	protected boolean outOfBounds(int width, int height){
		int buffer = 32;
		if (location.getX() > width+buffer){
			return true;
		}
		if (location.getX() < 0-buffer){
			return true;
		}
		if (location.getY() > height+buffer){
			return true;
		}
		if (location.getY() < 0-buffer){
			return true;
		}
		return false;
	}
	

	public void draw(World world) {
		AdvancedGraphics pen = world.getPen();
		
		pen.setColor(color);
		pen.fillCircle((int) location.getX(), (int) location.getY(), (int) radius);
	}



}
