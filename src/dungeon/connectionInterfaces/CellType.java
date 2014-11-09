package dungeon.connectionInterfaces;

public enum CellType {

	Wall(false), Floor(true), Door(true);

	public final boolean PASSABLE;

	CellType(final boolean passable) {
		PASSABLE = passable;
	}
}
