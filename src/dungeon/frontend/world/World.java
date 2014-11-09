package dungeon.frontend.world;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import dungeon.animation.AnimationFrame;
import dungeon.connectionInterfaces.CellType;
import dungeon.connectionInterfaces.DungeonManagerInterface;
import dungeon.frontend.graphicsSystem.AdvancedGraphics;
import dungeon.frontend.graphicsSystem.GameWorld;
import dungeon.util.physics.Point;

public class World extends GameWorld{

	//Passes the dungeon manager to the GameWorld Parent (manager is stored for reference later).
	public World(DungeonManagerInterface mainManager) {
		super(mainManager);
	}

	boolean inGame = true;
	
	//ArrayList<Wall> wallList
	
	@Override
	public void start() {
		
		loadImage("corner_bottom_left");
		loadImage("corner_bottom_right");
		loadImage("corner_top_left");
		loadImage("corner_top_right");
		loadImage("end_bottom");
		loadImage("end_left");
		loadImage("end_right");
		loadImage("end_top");
		loadImage("intersection_piece");
		loadImage("mid_horizontal");
		loadImage("mid_vertical");
		loadImage("single_piece");
		loadImage("t_bottom");
		loadImage("t_left");
		loadImage("t_right");
		loadImage("t_top");	
	}

	@Override
	public void update() {

		if (inGame){
			gameUpdate();
		}
	}
	
	public void gameUpdate(){
		//--------------
	}
	
	public void titleUpdate(){
		//----------------
	}
	
	
	@Override
	public void draw(){
		if (inGame){
			gameDraw();
		}
		else
			titleDraw();
	}
	

	public void titleDraw(){
		
		AdvancedGraphics pen = getPen();
		pen.setColor(new Color(100, 100,0));
	}

	public void gameDraw() {
		AdvancedGraphics pen = getPen();
		//drawGridLines();
		DungeonManagerInterface mainManager = getManager();
		int pX = mainManager.getPlayerX();
		int pY = mainManager.getPlayerY();
		Point playerLocation = new Point(pX, pY);
		

		
		pen.moveCameraPosition(-150, -100);
		AnimationFrame currentImage = mainManager.getPlayerAnimations().getNextImage();
		for (int x=-9; x<9; x++){
			for (int y=-6; y<6; y++){
				int coordX = pX + x;
				int coordY = pY + y;
				
				pen.setColor(new Color(255, 0 , 0));
				CellType cellType = mainManager.getCellTypeAt(coordX, coordY);
				if (cellType==CellType.Floor){
					pen.setColor(new Color(240, 240, 240));
					pen.fillRect(x* 20-currentImage.xDisplacment, y*20-currentImage.yDisplacment, 20, 20);
				}
				else if (cellType==CellType.Wall){
					pen.setColor(new Color(100, 100, 100));	
					BufferedImage image= getWallImage(coordX, coordY);
					pen.drawImage(image, x* 20-currentImage.xDisplacment, y*20-currentImage.yDisplacment, null);
					//pen.fillRect(x* 20-currentImage.xDisplacment, y*20-currentImage.yDisplacment, 20, 20);
				}
				
				
				pen.setColor(new Color(0,0,0, 100));
				//pen.drawRect(x*20, y*20, 20, 20);
			}
			
		}
		
		//Drawing the player-------
		//pen.setColor(new Color(0, 0, 255));
		//pen.fillCircle(0, 0, 20);
		
		
		pen.drawImage(currentImage.image, 0, 0, null);
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
	
	public BufferedImage getWallImage(int cX, int cY){
		DungeonManagerInterface m = getManager();
		
		boolean below = (wallBelow(cX, cY));
		boolean above = (wallAbove(cX, cY));
		boolean right = (wallRight(cX, cY));
		boolean left = (wallLeft(cX, cY));
		
		String image;
		
		//Singles----------------------------------
		if ((below)&&(!above)&&(!right)&&(!left)){
			image = "end_top";
		}
		else if ((!below)&&(above)&&(!right)&&(!left)){
			image = "end_bottom";
		}
		else if ((!below)&&(!above)&&(right)&&(!left)){
			image = "end_left";
		}
		else if ((!below)&&(!above)&&(!right)&&(left)){
			image = "end_right";
		}
		//-------------------------------------------
		
		
		//T-sections-------------------------------------
		else if ((below)&&(above)&&(right)&&(!left)){
			image = "t_right";
		}
		else if ((below)&&(above)&&(!right)&&(left)){
			image = "t_left";
		}
		else if ((below)&&(!above)&&(right)&&(left)){
			image = "t_bottom";
		}
		else if ((!below)&&(above)&&(right)&&(left)){
			image = "t_top";
		}
		//--------------------------------------------
		
		//two-corners------------------------------
		else if ((below)&&(!above)&&(right)&&(!left)){
			image = "corner_top_left";
		}
		
		else if ((below)&&(!above)&&(!right)&&(left)){
			image = "corner_top_right";
		}
		
		else if ((!below)&&(above)&&(right)&&(!left)){
			image = "corner_bottom_right";
		}
		
		else if ((!below)&&(above)&&(!right)&&(!left)){
			image = "corner_bottom_left";
		}
		//-------------------------------------
		
		//singles--------------------------
		else if ((!below)&&(!above)&&(!right)&&(!left)){
			image = "single_piece";
		}
		//-----------------------------------------
		
		//horizontals/verticals------------------------------
		else if ((!below)&&(!above)&&(right)&&(!left)){
			image = "mid_horizontal";
		}
		else if ((below)&&(above)&&(!right)&&(!left)){
			image = "mid_vertical";
		}
		
		//alls----------
		else if ((below)&&(above)&&(right)&&(left)){
			image = "intersection_piece";
		}
		
		
		else
			image = "single_piece";
		
		BufferedImage img = getImage(image);
		return img;
		
		
	}
	
	public boolean wallBelow(int coordX, int coordY){
		DungeonManagerInterface m = getManager();
		if (m.getCellTypeAt(coordX, coordY+1)==CellType.Wall){
			return true;
		}
		else
			return false;
	}
	
	public boolean wallAbove(int coordX, int coordY){
		DungeonManagerInterface m = getManager();
		if (m.getCellTypeAt(coordX, coordY-1)==CellType.Wall){
			return true;
		}
		else
			return false;
	}
	
	public boolean wallRight(int coordX, int coordY){
		DungeonManagerInterface m = getManager();
		if (m.getCellTypeAt(coordX+1, coordY)==CellType.Wall){
			return true;
		}
		else
			return false;
	}
	
	
	public boolean wallLeft(int coordX, int coordY){
		DungeonManagerInterface m = getManager();
		if (m.getCellTypeAt(coordX-1, coordY)==CellType.Wall){
			return true;
		}
		else
			return false;
	}

}
