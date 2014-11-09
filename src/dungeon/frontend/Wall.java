package dungeon.frontend;

import java.awt.Color;

import dungeon.frontend.graphicsSystem.AdvancedGraphics;
import dungeon.frontend.physics.Point;
import dungeon.frontend.world.World;

public class Wall {

	Point location;
	
	public Wall(int x, int y){
		location = new Point(x,y);
	}
	
	public void draw(World world){
		
		AdvancedGraphics pen = world.getPen();
		pen.setColor(new Color(100, 100, 100));	
		pen.fillRect((int)location.getX(), (int)location.getY(), 20, 20);
	}
	
}
