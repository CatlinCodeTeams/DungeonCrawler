package dungeon.backend;

import dungeon.animation.Animateable;
import dungeon.animation.character.PlayerAnimation;
import dungeon.backend.generation.DungeonGenerator;
import dungeon.backend.generation.DungeonGraph;
import dungeon.backend.player.PlayerManager;
import dungeon.connectionInterfaces.CellType;
import dungeon.connectionInterfaces.DungeonManagerInterface;
import dungeon.util.Direction;

public class DungeonManager implements DungeonManagerInterface {

	protected final PlayerManager playerManager = new PlayerManager();
	protected final DungeonGenerator gen;
	protected DungeonGraph layout;

	public DungeonManager(final DungeonType type) {
		gen = new DungeonGenerator(type);
		layout = gen.generate();
	}

	@Override
	public CellType getCellTypeAt(final int x, final int y) {
		return layout.getCellTypeAt(x, y);
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
		if(!playerManager.isAnimating() && layout.getCellTypeAt(getPlayerX(), getPlayerY()-1).PASSABLE){
			playerManager.tryAddAnimationInstance(PlayerAnimation.walkUp.copy());
			playerManager.y--;
			playerManager.currentDirection=Direction.NORTH;
		}
	}

	@Override
	public void downKeyPressed() {
		if(!playerManager.isAnimating() && layout.getCellTypeAt(getPlayerX(), getPlayerY()+1).PASSABLE){
			playerManager.tryAddAnimationInstance(PlayerAnimation.walkDown.copy());
			playerManager.y++;
			playerManager.currentDirection=Direction.SOUTH;
		}
	}

	@Override
	public void leftKeyPressed() {
		if(!playerManager.isAnimating() && layout.getCellTypeAt(getPlayerX()-1, getPlayerY()).PASSABLE){
			playerManager.tryAddAnimationInstance(PlayerAnimation.walkLeft.copy());
			playerManager.x--;
			playerManager.currentDirection=Direction.WEST;
		}
	}

	@Override
	public void rightKeyPressed() {
		if(!playerManager.isAnimating() && layout.getCellTypeAt(getPlayerX()+1, getPlayerY()).PASSABLE){
			playerManager.tryAddAnimationInstance(PlayerAnimation.walkRight.copy());
			playerManager.x++;
			playerManager.currentDirection=Direction.EAST;
		}
	}

	@Override
	public void resetKeyPressed() {
		layout = gen.generate();
		playerManager.x = 0;
		playerManager.y = 0;
	}

	@Override
	public Animateable getPlayerAnimations() {
		return playerManager;
	}
}
