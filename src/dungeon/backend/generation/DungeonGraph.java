package dungeon.backend.generation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dungeon.backend.generation.section.DungeonSection;
import dungeon.connectionInterfaces.CellType;
import dungeon.util.defualtMap.DefaultMap;
import dungeon.util.factory.Factory;
import dungeon.util.physics.Point;

public class DungeonGraph {

	private final Map<Integer, Map<Integer, CellType>> locations = DefaultMap
			.nestedDefualtMap(new Factory<CellType>() {
				@Override
				public CellType get() {
					return CellType.Wall;
				}
			});
	private int maxX;
	private int minX;
	private int maxY;
	private int minY;

	public void makeSectionFloor(final DungeonSection s) {
		for(Point p:s.getContents()){
			makeCellFloor((int)p.getX(),(int)p.getY());
		}
	}

	public void makeCellFloor(final int x, final int y) {
		if (x > maxX) {
			maxX = x;
		}
		if (x < minX) {
			minX = x;
		}
		if (y > maxY) {
			maxY = y;
		}
		if (y < minY) {
			minY = y;
		}
		locations.get(x).put(y, CellType.Floor);
	}

	public CellType getCellTypeAt(final int x, final int y) {
		return locations.get(x).get(y);
	}

	public List<Point> getAllLocationsOfCellType(CellType type){
		List<Point> locations=new ArrayList<>();
		for(int x=getMinX()-1;x<=getMaxX()+1;x++){
			for(int y=getMinY()-1;y<=getMaxY()+1;y++){
				if (getCellTypeAt(x,y)==type){
					locations.add(new Point(x,y));
				}
			}
		}
		return locations;
	}

	public int numNeighborsOfType(int x,int y,CellType type){
		int count=0;
		if(getCellTypeAt(x+1,y)==type) count++;
		if(getCellTypeAt(x-1,y)==type) count++;
		if(getCellTypeAt(x,y+1)==type) count++;
		if(getCellTypeAt(x,y-1)==type) count++;
		return count;
	}

	public Point pointOpositeNeighborOfType(int x,int y,CellType type){
		if(getCellTypeAt(x+1,y)==type) return new Point(x-2,y);
		if(getCellTypeAt(x-1,y)==type) return new Point(x+2,y);
		if(getCellTypeAt(x,y+1)==type) return new Point(x,y-2);
		if(getCellTypeAt(x,y-1)==type) return new Point(x,y+2);
		return null;
	}

	public boolean verifyFree(DungeonSection section){
		for(Point p:section.getContents()){
			if(getCellTypeAt((int)p.getX(),(int)p.getY())!=CellType.Wall){
				return false;
			}
		}
		return true;
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMinX() {
		return minX;
	}

	public int getMaxY() {
		return maxY;
	}

	public int getMinY() {
		return minY;
	}
}
