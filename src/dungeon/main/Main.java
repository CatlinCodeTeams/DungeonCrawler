package dungeon.main;

import dungeon.backend.RPGDisplay;
import dungeon.frontend.DungeonManager;

public class Main {

	public static void main(String[] args) {
		DungeonManager mainManager=new DungeonManager();
		new RPGDisplay(mainManager);
	}

}
