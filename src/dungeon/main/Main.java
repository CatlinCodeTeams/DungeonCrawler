package dungeon.main;

import dungeon.backend.DungeonManager;
import dungeon.backend.DungeonType;
import dungeon.frontend.RPGDisplay;

public class Main {

	public static void main(String[] args) {
		DungeonManager mainManager=new DungeonManager(DungeonType.BASEMENT);
		new RPGDisplay(mainManager);
	}

}
