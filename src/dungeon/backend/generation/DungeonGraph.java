package dungeon.backend.generation;

import java.util.Map;

import dungeon.connectionInterfaces.CellType;
import dungeon.util.defualtMap.DefaultMap;
import dungeon.util.defualtMap.Factory;

public class DungeonGraph {

	private Map<Integer,Map<Integer,CellType>> locations=DefaultMap.nestedDefualtMap(new Factory<CellType>(){
		@Override
		public CellType get() {
			return CellType.Wall;
		}
	});

	public void makeCellFloor(int x, int y){
		locations.get(x).put(y, CellType.Floor);
	}

	public CellType getCellTypeAt(int x, int y) {
		return locations.get(x).get(y);
	}

}
