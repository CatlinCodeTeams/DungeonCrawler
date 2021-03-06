package dungeon.frontend.world;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import dungeon.animation.AnimationFrame;
import dungeon.backend.enemy.Enemy;
import dungeon.connectionInterfaces.CellType;
import dungeon.connectionInterfaces.DungeonManagerInterface;
import dungeon.frontend.graphicsSystem.AdvancedGraphics;
import dungeon.frontend.graphicsSystem.GameWorld;
import dungeon.util.physics.Point;
import dungeon.util.physics.Vector;

public class World extends GameWorld{

	//Passes the dungeon manager to the GameWorld Parent (manager is stored for reference later).
	public World(DungeonManagerInterface mainManager) {
		super(mainManager);
	}

	boolean inGame = false;
	boolean viewMap = false;

	int fadeIn = 255;

	ArrayList<Particle> partList;

	@Override
	public void start() {
		
		partList = new ArrayList<Particle>();
		
		playSound("8_bit45alt.aif");

		loadImage("ChestClosed");
		loadImage("ChestOpened");

		loadImage("titleScreen");

		loadImage("Ladder");

		loadImage("light");

		loadImage("Torch0");
		loadImage("Torch1");
		loadImage("Torch2");
		loadImage("Torch3");
		loadImage("TorchOff");
		loadImage("large_stone_tile");
		loadImage("Door");
		loadImage("DoorOpen");
		loadImage("OpenDoorRight");
		loadImage("DoorSide");

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
		else
			titleUpdate();


	}

	public void gameUpdate(){
		
		for (Particle p: partList){
			p.update(this);
		}

		DungeonManagerInterface mainManager = getManager();

		if ((isKeyPressed('a'))||(isKeyPressed(37))){
			mainManager.leftKeyPressed();
		}
		if ((isKeyPressed('w'))||(isKeyPressed(38))){
			mainManager.upKeyPressed();
		}
		if ((isKeyPressed('d'))||(isKeyPressed(39))){
			mainManager.rightKeyPressed();
		}
		if ((isKeyPressed('s'))||(isKeyPressed(40))){
			mainManager.downKeyPressed();
		}

		if (isKeyPressed('m')){
			viewMap = true;
		}
		else
			viewMap = false;
	}

	public void titleUpdate(){
		if (fadeIn>0){
			fadeIn--;
		}
	}


	@Override
	public void draw(){
		if (inGame){
			gameDraw();
		}
		else
			titleDraw();
	}
	
	public static synchronized void playSound(final String name) {
		  new Thread(new Runnable() {
		  // The wrapper thread is unnecessary, unless it blocks on the
		  // Clip finishing; see comments.
		    public void run() {
		      try {
		    	File file = new File(("assets"+File.separator+name));
		    	AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
		    	
		        Clip clip = AudioSystem.getClip();
		        clip.open(inputStream);
		        clip.start(); 
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
		    }
		  }).start();
		}


	public void titleDraw(){

		AdvancedGraphics pen = getPen();

		pen.setColor(new Color(255, 255, 255));
		pen.drawRect(0, 0, 299, 199);


		drawImage("titleScreen", 0, 0, 300, 200);


		pen.setColor(new Color(0,0,0,fadeIn));
		pen.fillRect(0, 0, 300, 200);
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
					//pen.fillRect(x* 20-currentImage.xDisplacment, y*20-currentImage.yDisplacment, 20, 20);
					drawImage("large_stone_tile", x* 20-currentImage.xDisplacment, y*20-currentImage.yDisplacment, 20, 20);
				}
				else if (cellType==CellType.Wall){
					pen.setColor(new Color(100, 100, 100));
					BufferedImage image= getWallImage(coordX, coordY);
					pen.drawImage(image, x* 20-currentImage.xDisplacment, y*20-currentImage.yDisplacment, null);
					//pen.fillRect(x* 20-currentImage.xDisplacment, y*20-currentImage.yDisplacment, 20, 20);


					//--------------------------------------------Shadow Casting
/*					ArrayList<Point> points = new ArrayList<Point>();
					ArrayList<Point> points2 = new ArrayList<Point>();
					Point p1 = new Point(x* 20-currentImage.xDisplacment, y*20-currentImage.yDisplacment);
					Point p2 = new Point(x* 20-currentImage.xDisplacment, y*20-currentImage.yDisplacment+20);
					Point p3 = new Point(x* 20-currentImage.xDisplacment+20, y*20-currentImage.yDisplacment);
					Point p4 = new Point(x* 20-currentImage.xDisplacment+20, y*20-currentImage.yDisplacment+20);

					points.add(p1);
					points.add(p2);
					points.add(p3);
					points.add(p4);
					
					points2.add(p1);
					points2.add(p2);
					points2.add(p3);
					points2.add(p4);

					Point pLoc = new Point(0,0);
					for (Point p: points2){
						Vector vec = pLoc.makeVector(p);
						p.move(vec);
					}
					
					
					int[] xPoints= new int[8];
					for (int i=0; i<4; i++){
						xPoints[i] = (int)points.get(i).getX();
						xPoints[i+4] = (int)points2.get(i).getX();
					}
					
					int[] yPoints= new int[8];
					for (int i=0; i<4; i++){
						yPoints[i] = (int)points.get(i).getY();
						yPoints[i+4] = (int)points2.get(i).getY();
					}
					pen.drawPolygon(xPoints, yPoints, 8);*/

					//-----------------------------

				}

				else if (cellType == CellType.OpenDoor){
					drawImage("large_stone_tile", x* 20-currentImage.xDisplacment, y*20-currentImage.yDisplacment, 20, 20);
					
					if ((wallBelow(coordX, coordY)||(doorBelow(coordX, coordY)))&&(wallAbove(coordX, coordY)||(doorAbove(coordX, coordY)))){
						drawImage("OpenDoorRight", x* 20-currentImage.xDisplacment, y*20-currentImage.yDisplacment, 20, 20);
					}
					else
						drawImage("DoorOpen", x* 20-currentImage.xDisplacment, y*20-currentImage.yDisplacment, 20, 20);

				}

				else if (cellType == CellType.ClosedDoor){
					drawImage("large_stone_tile", x* 20-currentImage.xDisplacment, y*20-currentImage.yDisplacment, 20, 20);
					
					if ((wallBelow(coordX, coordY)||(doorBelow(coordX, coordY)))&&(wallAbove(coordX, coordY)||(doorAbove(coordX, coordY)))){
						drawImage("DoorSide", x* 20-currentImage.xDisplacment, y*20-currentImage.yDisplacment, 20, 20);
					}
					else
						drawImage("Door", x* 20-currentImage.xDisplacment, y*20-currentImage.yDisplacment, 20, 20);
				
				}

				else if (cellType == CellType.ClosedChest){
					drawImage("large_stone_tile", x* 20-currentImage.xDisplacment, y*20-currentImage.yDisplacment, 20, 20);
					drawImage("ChestClosed", x* 20-currentImage.xDisplacment, y*20-currentImage.yDisplacment, 20, 20);
				}

				else if (cellType == CellType.OpenChest){
					drawImage("large_stone_tile", x* 20-currentImage.xDisplacment, y*20-currentImage.yDisplacment, 20, 20);
					drawImage("ChestOpened", x* 20-currentImage.xDisplacment, y*20-currentImage.yDisplacment, 20, 20);
				}

				else if (cellType == CellType.Stair){
					drawImage("large_stone_tile", x* 20-currentImage.xDisplacment, y*20-currentImage.yDisplacment, 20, 20);
					drawImage("Ladder", x* 20-currentImage.xDisplacment, y*20-currentImage.yDisplacment, 20, 20);
				}

				else if (cellType == CellType.ExtinguishedTorch){
					drawImage("large_stone_tile", x* 20-currentImage.xDisplacment, y*20-currentImage.yDisplacment, 20, 20);
					drawImage("TorchOff", x* 20-currentImage.xDisplacment, y*20-currentImage.yDisplacment, 20, 20);

				}

				else if (cellType == CellType.Torch){
					drawImage("large_stone_tile", x* 20-currentImage.xDisplacment, y*20-currentImage.yDisplacment, 20, 20);
					drawImage("Torch0", x* 20-currentImage.xDisplacment, y*20-currentImage.yDisplacment, 20, 20);
				}


				pen.setColor(new Color(0,0,0, 100));
				//pen.drawRect(x*20, y*20, 20, 20);
			}

		}
		boolean anyAnimating=false;
		for (int x=-9; x<9; x++){
			for (int y=-6; y<6; y++){
				int coordX = pX + x;
				int coordY = pY + y;
				Enemy target=mainManager.getEnemyAt(coordX, coordY);
				if (target!=null){
					if(target.isAnimating()){
						anyAnimating=true;
					}
					AnimationFrame targetFrame=target.getNextImage();
					pen.drawImage(targetFrame.image, x* 20-currentImage.xDisplacment-targetFrame.xDisplacment, y*20-currentImage.yDisplacment-targetFrame.yDisplacment, null);
					if(target.health<=10){
						partList.add(new SmokeParticle(x* 20-currentImage.xDisplacment-targetFrame.xDisplacment+10, y*20-currentImage.yDisplacment-targetFrame.yDisplacment+10));
					}
				}
			}
		}
		if(!anyAnimating){
			mainManager.confirmNoVisableEnemyAnimations();
		}

		//Drawing the player-------
		//pen.setColor(new Color(0, 0, 255));
		//pen.fillCircle(0, 0, 20);

		pen.setColor(new Color(0,0,0,100));
		pen.fillOval(2, 12, 16, 10);
		pen.drawImage(currentImage.image, 0, 0, null);
		//

		
		for (Particle p: partList){
			p.draw(this);
		}

		//Reset Camera for HUD
		pen.moveCameraPosition(150, 100);

		drawImage("light", 0, 0, 330, 220);

		if (!viewMap){
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

					else if (cellType==CellType.OpenDoor){
						pen.setColor(new Color(40, 200,40, 150));
						pen.fillRect(((x+18)* 2)+230, (y+13)*2, 2, 2);
					}

					else if (cellType==CellType.ClosedDoor){
						pen.setColor(new Color(200, 40,40, 150));
						pen.fillRect(((x+18)* 2)+230, (y+13)*2, 2, 2);
					}

					else if (cellType == CellType.ClosedChest){
						pen.setColor(new Color(40, 40,200, 150));
						pen.fillRect(((x+18)* 2)+230, (y+13)*2, 2, 2);
					}
					else if (cellType == CellType.Torch){
						pen.setColor(new Color(255, 70,40, 150));
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


			//health bar shadow
			pen.setColor(new Color(0, 0, 0, 140));
			pen.fillRect(150+2, 180-3, 150, 20);

			//health bar backing
			pen.setColor(new Color(200, 200, 200, 200));
			pen.fillRect(150, 180, 150, 20);


			double health = mainManager.getCurrentPlayerHealth();
			double maxHealth = 1000;

			double percent = health/maxHealth;
			int width = (int)(140*percent);


			//health bar slot
			pen.setColor(new Color(0, 0, 0, 60));
			pen.fillRect(156 , 183, 140, 15);

			//health bar
			pen.setColor(new Color(200, 20, 20, 200));
			pen.fillRect(156 + (140-width), 183, width, 15);
			
			
			
			//Hud backings
			//pen.setColor(new Color(200, 200, 200, 200));
			//pen.fillRect(0, 180, 20, 100);


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

	@Override
	public void keyTriggered(KeyEvent event){

		if (!inGame){
			inGame = true;
		}

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

		if (event.getKeyChar()==' '){
			mainManager.interactKeyPressed();
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
		else if ((!below)&&(above)&&(!right)&&(left)){
			image = "corner_bottom_right";
		}

		else if ((!below)&&(above)&&(right)&&(!left)){
			image = "corner_bottom_left";
		}

		else if ((below)&&(!above)&&(!right)&&(left)){
			image = "corner_top_right";
		}

		else if ((below)&&(!above)&&(right)&&(!left)){
			image = "corner_top_left";
		}
		//-------------------------------------

		//singles--------------------------
		else if ((!below)&&(!above)&&(!right)&&(!left)){
			image = "single_piece";
		}
		//-----------------------------------------

		//horizontals/verticals------------------------------
		else if ((!below)&&(!above)&&(right)&&(left)){
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
	
	public boolean doorAbove(int coordX, int coordY){
		DungeonManagerInterface m = getManager();
		if ((m.getCellTypeAt(coordX, coordY-1)==CellType.OpenDoor)||(m.getCellTypeAt(coordX, coordY+1)==CellType.ClosedDoor)){
			return true;
		}
		else
			return false;
	}
	
	public boolean doorBelow(int coordX, int coordY){
		DungeonManagerInterface m = getManager();
		if ((m.getCellTypeAt(coordX, coordY+1)==CellType.OpenDoor)||(m.getCellTypeAt(coordX, coordY+1)==CellType.ClosedDoor)){
			return true;
		}
		else
			return false;
	}
	
	public boolean doorLeft(int coordX, int coordY){
		DungeonManagerInterface m = getManager();
		if ((m.getCellTypeAt(coordX-1, coordY)==CellType.OpenDoor)||(m.getCellTypeAt(coordX, coordY+1)==CellType.ClosedDoor)){
			return true;
		}
		else
			return false;
	}
	
	public boolean doorRight(int coordX, int coordY){
		DungeonManagerInterface m = getManager();
		if ((m.getCellTypeAt(coordX+1, coordY)==CellType.OpenDoor)||(m.getCellTypeAt(coordX, coordY+1)==CellType.ClosedDoor)){
			return true;
		}
		else
			return false;
	}

}