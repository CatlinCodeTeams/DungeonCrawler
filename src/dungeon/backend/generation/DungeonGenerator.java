package dungeon.backend.generation;

import dungeon.backend.DungeonType;

public class DungeonGenerator {
	public final DungeonType targetType;
	public DungeonGenerator(DungeonType type){
		targetType=type;
	}
	public DungeonGraph generate() {
		return new DungeonGraph();
	}
}
