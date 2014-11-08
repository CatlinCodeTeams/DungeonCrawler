package dungeon.frontend;

import dungeon.frontend.GamePanel;
import dungeon.frontend.GameWorld;

import javax.swing.JFrame;

public class GameWindow {


	final JFrame mainFrame;
	GamePanel gamePanel;
	
	int GAME_SCALE = 2;
	int GAME_WIDTH = 320;
	int GAME_HEIGHT = 240;
	
	
	public GameWindow(GameWorld world){
		
		//Create the Jframe.
		mainFrame = new JFrame("Game Window");
		//Set attributes of the Jframe.----:
		//Window closes by default
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);

		//Creates a new "World"
		//Makes the gamePanel, and adds it to the Jframe.
		//(the world is fed to the GamePanel constructor)
		gamePanel = new GamePanel(world, GAME_WIDTH, GAME_HEIGHT, GAME_SCALE);
		mainFrame.getContentPane().add(gamePanel);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		mainFrame.pack();
		
		

	}	
	
}
