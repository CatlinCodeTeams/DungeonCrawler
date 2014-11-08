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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPlayerY() {
		// TODO Auto-generated method stub
		return 0;
	}

}
