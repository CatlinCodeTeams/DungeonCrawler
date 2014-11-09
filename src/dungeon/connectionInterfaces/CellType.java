package dungeon.connectionInterfaces;

public enum CellType {

	Floor(true), Stair(true), ClosedDoor(false), OpenDoor(true), Wall(false), Torch(false), ExtinguishedTorch(false), OpenChest(false), ClosedChest(false);
	static{
		ClosedDoor.setTransform(OpenDoor);
		OpenDoor.setTransform(ClosedDoor);

		Torch.setTransform(ExtinguishedTorch);
		ExtinguishedTorch.setTransform(Torch);

		OpenChest.setTransform(ClosedChest);
		ClosedChest.setTransform(OpenChest);
	}

	public final boolean PASSABLE;
	public CellType transform;

	CellType(final boolean passable) {
		PASSABLE=passable;
		transform=this;
	}
	public void setTransform(CellType type){
		transform=type;
	}
}
