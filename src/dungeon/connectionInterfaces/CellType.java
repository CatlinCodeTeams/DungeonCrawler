package dungeon.connectionInterfaces;

public enum CellType {

	Wall(false), Floor(true), OpenDoor(true), ClosedDoor(false);

	public final boolean PASSABLE;

	CellType(final boolean passable) {
		PASSABLE = passable;
	}
}
