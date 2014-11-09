package dungeon.frontend.world;

import java.awt.Color;
import java.awt.event.KeyEvent;

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
		
		
		for (int x=0; x<16; x++){
			for (int y=0; y<11; y++){
				int coordX = pX + x;
				int coordY = pY + y;
				
				pen.setColor(new Color(255, 0 , 0));
				CellType cellType = mainManager.getCellTypeAt(coordX, coordY);
				if (cellType==CellType.Floor){
					pen.setColor(new Color(240, 240, 240));
				}
				else if (cellType==CellType.Wall){
					pen.setColor(new Color(100, 100, 100));	
				}
				
				pen.fillRect(x* 20, y*20, 20, 20);
				pen.setColor(new Color(0,0,0));
				pen.drawRect(x*20, y*20, 20, 20);
			}
			
		}
		
		pen.setColor(new Color(0, 0, 255));
		//pen.fillCircle(pX*20, pY*20, 16);
		
		
		
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
	
	@Override
	public void keyTriggered(KeyEvent event){
		
		DungeonManagerInterface mainManager = getManager();
		
		//Left
		if (event.getExtendedKeyCode()==37){
			mainManager.leftKeyPressed();
		}
		//Up
		if (event.getExtendedKeyCode()==38){
			mainManager.upKeyPressed();
		}
		//Right
		if (event.getExtendedKeyCode()==39){
			mainManager.rightKeyPressed();
		}
		//Down
		if (event.getExtendedKeyCode()==40){
			mainManager.downKeyPressed();
		}
		if (event.getKeyChar()=='r'){
			mainManager.resetKeyPressed();
		}
			
	}

}
