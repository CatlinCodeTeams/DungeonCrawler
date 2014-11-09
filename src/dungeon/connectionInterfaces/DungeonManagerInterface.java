package dungeon.connectionInterfaces;

import dungeon.animation.Animateable;
import dungeon.backend.enemy.Enemy;

public interface DungeonManagerInterface {
	public Enemy getEnemyAt(int x,int y);

	public CellType getCellTypeAt(int x, int y);

	public int getPlayerX();

	public int getPlayerY();

	public void upKeyPressed();

	public void downKeyPressed();

	public void leftKeyPressed();

	public void rightKeyPressed();

	public void resetKeyPressed();

	public void interactKeyPressed();

	public Animateable getPlayerAnimations();
}
