package dungeon.frontend;

import javax.swing.SwingUtilities;

import dungeon.connectionInterfaces.DungeonManagerInterface;
import dungeon.frontend.graphicsSystem.GameWindow;
import dungeon.frontend.world.World;

public class RPGDisplay {

	public RPGDisplay(final DungeonManagerInterface mainManager) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GameWindow(new World(mainManager));
			}
		});
	}
}
