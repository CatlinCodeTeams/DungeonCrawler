package dungeon.main;

import dungeon.backend.DungeonManager;
import dungeon.frontend.RPGDisplay;

public class Main {

	public static void main(String[] args) {
		DungeonManager mainManager=new DungeonManager();
		new RPGDisplay(mainManager);
	}

}
