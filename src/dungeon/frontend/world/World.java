package dungeon.frontend.world;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import dungeon.animation.AnimationFrame;
import dungeon.connectionInterfaces.CellType;
import dungeon.connectionInterfaces.DungeonManagerInterface;
import dungeon.frontend.Camera;
import dungeon.frontend.graphicsSystem.AdvancedGraphics;
import dungeon.frontend.graphicsSystem.GameWorld;
import dungeon.frontend.physics.Point;

public class World extends GameWorld{

	//Passes the dungeon manager to the GameWorld Parent (manager is stored for reference later).
	public World(DungeonManagerInterface mainManager) {
		super(mainManager);
	}

	int angle;
	Camera cam;
	
	//ArrayList<Wall> wallList
	
	@Override
	public void start() {
		angle = 0;
		cam = new Camera(new Point(0,0), 0, 0);
		
	}

	@Override
	public void update() {
		angle ++;
		
		DungeonManagerInterface mainManager = getManager();
		int pX = mainManager.getPlayerX();
		int pY = mainManager.getPlayerY();
		Point playerLocation = new Point(pX, pY);
		
		cam.update(playerLocation);
		
	}

	@Override
	public void draw() {
		AdvancedGraphics pen = getPen();
		//drawGridLines();
		DungeonManagerInterface mainManager = getManager();
		int pX = mainManager.getPlayerX();
		int pY = mainManager.getPlayerY();
		Point playerLocation = new Point(pX, pY);
		

		
		//pen.moveCameraPosition((int)(-1*(cam.getX())), (int)(-1*(cam.getY())));
		pen.moveCameraPosition(-150, -100);
		
		
		for (int x=-8; x<8; x++){
			for (int y=-5; y<5; y++){
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
				pen.setColor(new Color(0,0,0, 100));
				//pen.drawRect(x*20, y*20, 20, 20);
			}
			
		}
		
		//Drawing the player-------
		//pen.setColor(new Color(0, 0, 255));
		//pen.fillCircle(0, 0, 20);
		
		AnimationFrame currentImage = mainManager.getPlayerAnimations().getNextImage();
		pen.drawImage(currentImage.image, 0+currentImage.xDisplacment, 0+currentImage.yDisplacment, null);
		//
		
		
		//Reset Camera for HUD
		pen.moveCameraPosition(150, 100);
		
		pen.setColor(new Color(0, 0, 0, 140));
		pen.fillRect(230, 0, 70+2, 50+2);

		
		for (int x=-18; x<18; x++){
			for (int y=-13; y<12; y++){
				int coordX = pX + x;
				int coordY = pY + y;
				
				pen.setColor(new Color(255, 0 , 0));
				CellType cellType = mainManager.getCellTypeAt(coordX, coordY);

				if (cellType==CellType.Wall){
					
					
					pen.setColor(new Color(255, 255, 255, 150));	
					pen.fillRect(((x+18)* 2)+230, (y+13)*2, 2, 2);
				}
				
				else if (cellType==CellType.Floor){
					pen.setColor(new Color(40, 40,40, 150));
					pen.fillRect(((x+18)* 2)+230, (y+13)*2, 2, 2);
				}
				
				
				pen.setColor(new Color(0,0,0, 100));
				//pen.drawRect(x*20, y*20, 20, 20);
			}
			
		}
		
		pen.setColor(new Color(0, 0, 0, 100));
		pen.fillRect(230+(18*2)+1, (13*2)+1, 2, 2);
		
		pen.setColor(Color.BLUE);
		pen.fillRect(230+(18*2), (13*2), 2, 2);

		
		
		
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
		if ((event.getExtendedKeyCode()==37)||(event.getKeyChar()=='a')){
			mainManager.leftKeyPressed();
		}
		//Up
		if ((event.getExtendedKeyCode()==38)||(event.getKeyChar()=='w')){
			mainManager.upKeyPressed();
		}
		//Right
		if ((event.getExtendedKeyCode()==39)||(event.getKeyChar()=='d')){
			mainManager.rightKeyPressed();
		}
		//Down
		if ((event.getExtendedKeyCode()==40)||(event.getKeyChar()=='s')){
			mainManager.downKeyPressed();
		}
		if (event.getKeyChar()=='r'){
			mainManager.resetKeyPressed();
		}
			
	}

}
