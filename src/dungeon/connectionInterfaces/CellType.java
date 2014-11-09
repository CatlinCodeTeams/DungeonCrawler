package dungeon.connectionInterfaces;

public enum CellType {

	Floor(true), Stair(true), OpenDoor(true), ClosedDoor(false), Wall(false), Torch(false), OpenChest(false), ClosedChest(false);

	public final boolean PASSABLE;
	public final CellType transform;

	CellType(final boolean passable) {
		this(passable,null);
	}
	CellType(final boolean passable,CellType interactTransform){
		PASSABLE = passable;
		transform=interactTransform==null?this:interactTransform;

	}
}
