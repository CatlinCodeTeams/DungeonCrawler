package dungeon.frontend;

import javax.swing.SwingUtilities;

import dungeon.connectionInterfaces.DungeonManagerInterface;
import dungeon.frontend.graphicsSystem.GameWindow;
import dungeon.frontend.world.World;

public class RPGDisplay {

	
	
	public RPGDisplay(final DungeonManagerInterface mainManager) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				//ALL CONSTRUCTOR CODE HERE:
				GameWindow gWindow = new GameWindow(new World(mainManager));
				//------------
			}
		});
	}
}
