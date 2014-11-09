package dungeon.main;

import dungeon.backend.DungeonManager;
import dungeon.frontend.RPGDisplay;
import dungeon.levelConfig.DungeonType;

public class Main {

	public static void main(final String[] args) {

		final DungeonManager mainManager = new DungeonManager(
				DungeonType.BASEMENT);
		new RPGDisplay(mainManager);
		

	}
	
	

}
