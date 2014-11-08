package dungeon.connectionInterfaces;

public enum CellType {

		Wall(false),Floor(true);

		public final boolean PASSABLE;

		CellType(boolean passable){
			PASSABLE=passable;
		}
}
