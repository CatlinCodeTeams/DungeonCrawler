package dungeon.frontend.graphicsSystem;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

//World object is where all the game's essential functions are run from. (start, update, draw, and essential listeners).

//I'm unsure as to why I need this at the moment. I think that I could just take care of it by having a bunch of blank bodies in the abstract 
//GameWorld class.
interface GameWorldInterface {
	

	public void start();
	public void update();
	public void draw();
	
	public boolean isKeyPressed(char c);
	public boolean isKeyPressed(int kc);
	
	
	public void mouseClicked(MouseEvent event);
	public void mouseEntered(MouseEvent event);
	public void mouseExited(MouseEvent event);
	public void mousePressed(MouseEvent event);
	public void mouseReleased(MouseEvent event);
	public void mouseDragged(MouseEvent event);
	public void mouseMoved(MouseEvent event);
	
	public void keyPressed(KeyEvent event);
	public void keyReleased(KeyEvent event);
	public void keyTyped(KeyEvent event);
	
	
	

}
