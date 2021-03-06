package dungeon.frontend.graphicsSystem;

import javax.swing.JFrame;

public class GameWindow {

	final JFrame mainFrame;
	GamePanel gamePanel;

	int GAME_SCALE = 3;
	int GAME_WIDTH = 300;
	int GAME_HEIGHT = 200;

	public GameWindow(final GameWorld world) {

		// Create the Jframe.
		mainFrame = new JFrame("Dungeon Crawler");
		// Set attributes of the Jframe.----:
		// Window closes by default
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);

		// Creates a new "World"
		// Makes the gamePanel, and adds it to the Jframe.
		// (the world is fed to the GamePanel constructor)
		gamePanel = new GamePanel(world, GAME_WIDTH, GAME_HEIGHT, GAME_SCALE);
		mainFrame.getContentPane().add(gamePanel);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		mainFrame.pack();

	}

}
