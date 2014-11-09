package dungeon.connectionInterfaces;

public interface DungeonManagerInterface {
	public CellType getCellTypeAt(int x, int y);

	public int getPlayerX();

	public int getPlayerY();

	public void upKeyPressed();

	public void downKeyPressed();

	public void leftKeyPressed();

	public void rightKeyPressed();

	public void resetKeyPressed();
}
