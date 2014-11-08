package dungeon.frontend;

import javax.swing.SwingUtilities;

import dungeon.connectionInterfaces.DungeonManagerInterface;

public class RPGDisplay {

	
	
	public RPGDisplay(final DungeonManagerInterface mainManager) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				//ALL CONSTRUCTOR CODE HERE
				
				GameWindow gWindow = new GameWindow(new World());
				
				
			}
		});
	}
}
