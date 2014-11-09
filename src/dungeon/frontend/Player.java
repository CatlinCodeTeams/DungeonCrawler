package dungeon.frontend;

import dungeon.animation.AnimationFrame;
import dungeon.connectionInterfaces.DungeonManagerInterface;
import dungeon.frontend.graphicsSystem.AdvancedGraphics;
import dungeon.frontend.world.World;
import dungeon.util.physics.Point;

public class Player {

	Point location;
	
	public Player(int x, int y){
		location = new Point(x,y);
	}
	
	public void draw(int x, int y, World world){
		
		location = new Point(x,y);
		
		AdvancedGraphics pen = world.getPen();
		DungeonManagerInterface mainManager = world.getManager();
		AnimationFrame currentImage = mainManager.getPlayerAnimations().getNextImage();
		
		int dX = (int)location.getX();
		int dY = (int)location.getY();
		
		pen.drawImage(currentImage.image, dX+currentImage.xDisplacment, dY+currentImage.yDisplacment, null);
	}
	
}
