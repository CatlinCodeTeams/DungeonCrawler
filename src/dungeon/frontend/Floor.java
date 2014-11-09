package dungeon.frontend;

import java.awt.Color;

import dungeon.frontend.graphicsSystem.AdvancedGraphics;
import dungeon.frontend.physics.Point;
import dungeon.frontend.world.World;

public class Floor {

	Point location;
	
	public Floor(int x, int y){
		location = new Point(x,y);
	}
	
	public void draw(World world){
		
		AdvancedGraphics pen = world.getPen();
		pen.setColor(new Color(240, 240, 240));
		pen.fillRect((int)location.getX(), (int)location.getY(), 20, 20);
	}
	
}
