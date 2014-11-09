package dungeon.backend.generation;

import java.util.Map;

import dungeon.backend.generation.section.DungeonSection;
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

	public void makeSectionFloor(DungeonSection s){
		for(int x=0;x<s.width;x++){
			for(int y=0;y<s.height;y++){
				makeCellFloor(s.startX+x, s.startY+y);
			}
		}
	}
	public void makeCellFloor(int x, int y){
		locations.get(x).put(y, CellType.Floor);
	}

	public CellType getCellTypeAt(int x, int y) {
		return locations.get(x).get(y);
	}

}
