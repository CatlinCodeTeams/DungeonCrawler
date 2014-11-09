package dungeon.main;

import dungeon.backend.DungeonManager;
import dungeon.backend.DungeonType;
import dungeon.frontend.RPGDisplay;

public class Main {

	public static void main(final String[] args) {

		final DungeonManager mainManager = new DungeonManager(
				DungeonType.BASEMENT);
		new RPGDisplay(mainManager);
		

	}
	
	

}
