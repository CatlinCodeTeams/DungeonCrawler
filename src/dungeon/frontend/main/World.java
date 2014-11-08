package dungeon.frontend.main;

import java.awt.Color;

import dungeon.frontend.graphicsSystem.AdvancedGraphics;
import dungeon.frontend.graphicsSystem.GameWorld;

public class World extends GameWorld{

	
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
		
		drawGridLines();
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
