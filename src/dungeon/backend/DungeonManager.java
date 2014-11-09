package dungeon.backend;

import dungeon.backend.generation.DungeonGenerator;
import dungeon.backend.generation.DungeonGraph;
import dungeon.backend.player.PlayerManager;
import dungeon.connectionInterfaces.CellType;
import dungeon.connectionInterfaces.DungeonManagerInterface;

public class DungeonManager implements DungeonManagerInterface{

	protected final PlayerManager playerManager=new PlayerManager();
	protected final DungeonGenerator gen;
	protected DungeonGraph layout;

	public DungeonManager(DungeonType type){
		gen=new DungeonGenerator(type);
		layout=gen.generate();
	}

	@Override
	public CellType getCellTypeAt(int x, int y) {
		return layout.getCellTypeAt(x,y);
	}

	@Override
	public int getPlayerX() {
		return playerManager.x;
	}

	@Override
	public int getPlayerY() {
		return playerManager.y;
	}

	@Override
	public void upKeyPressed() {
		playerManager.y--;
	}

	@Override
	public void downKeyPressed() {
		playerManager.y++;
	}

	@Override
	public void leftKeyPressed() {
		playerManager.x--;
	}

	@Override
	public void rightKeyPressed() {
		playerManager.x++;
	}

	@Override
	public void resetKeyPressed() {
		layout=gen.generate();
	}
}
