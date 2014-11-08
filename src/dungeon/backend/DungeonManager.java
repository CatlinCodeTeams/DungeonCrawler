package dungeon.backend;

import dungeon.backend.generation.DungeonGenerator;
import dungeon.backend.generation.DungeonGraph;
import dungeon.connectionInterfaces.CellType;
import dungeon.connectionInterfaces.DungeonManagerInterface;

public class DungeonManager implements DungeonManagerInterface{
	protected final DungeonGraph layout;

	public DungeonManager(DungeonType type){
		layout=new DungeonGenerator(type).generate();
	}

	@Override
	public CellType getCellTypeAt(int x, int y) {
		return layout.getCellTypeAt(x,y);
	}

	@Override
	public int getPlayerX() {
		return 0;
	}

	@Override
	public int getPlayerY() {
		return 0;
	}

	@Override
	public void upKeyPressed() {

	}

	@Override
	public void downKeyPressed() {}

	@Override
	public void leftKeyPressed() {}

	@Override
	public void rightKeyPressed() {}
}
