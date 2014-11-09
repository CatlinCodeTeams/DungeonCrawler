package dungeon.frontend.world;

import java.awt.Color;

import dungeon.connectionInterfaces.CellType;
import dungeon.connectionInterfaces.DungeonManagerInterface;
import dungeon.frontend.graphicsSystem.AdvancedGraphics;
import dungeon.frontend.graphicsSystem.GameWorld;
import dungeon.frontend.physics.Point;

public class World extends GameWorld{

	//Passes the dungeon manager to the GameWorld Parent (manager is stored for reference later).
	public World(DungeonManagerInterface mainManager) {
		super(mainManager);
	}

	int angle;
	
	@Override
	public void start() {
		angle = 0;
		
	}

	@Override
	public void update() {
		angle ++;
		
	}

	@Override
	public void draw() {
		AdvancedGraphics pen = getPen();
		//drawGridLines();
		DungeonManagerInterface mainManager = getManager();
		int pX = mainManager.getPlayerX();
		int pY = mainManager.getPlayerY();
		Point playerLocation = new Point(pX, pY);
		pen.moveCameraPosition(pX-150, pY-100);

		pen.drawCenteredCircle(0, 0, 32);
		for (int x=-7; x<7; x++){
			for (int y=-5; y<5; y++){
				int coordX = pX + x;
				int coordY = pY + y;
				
				CellType cellType = mainManager.getCellTypeAt(coordX, coordY);
			}
			
		}
		
		
		
	}
	
	public void drawGridLines(){
		AdvancedGraphics pen = getPen();
		for (int x=0; x<15; x++){
			pen.drawLine(20*x, 0, 20*x, 200);
		}
		for (int y=0; y<10; y++){
			pen.drawLine(0, 20*y, 300, 20*y);
		}
	}

}
