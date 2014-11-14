package dungeon.frontend.world;

import java.awt.Color;

import dungeon.util.physics.Point;
import dungeon.util.physics.Vector;

public class SmokeParticle extends Particle{
	
	Vector gravity = new Vector(0,-.1);

	public SmokeParticle(int x, int y) {
		super(x, y, new Color(80,80,80, 150), 2, 1);
		// TODO Auto-generated constructor stub
	}
	
	public SmokeParticle(Point location){
		super(location, new Color(80,80,80, 150), 2, 1);
	}
	
	@Override
	public void update(World world){
		
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
	


}
