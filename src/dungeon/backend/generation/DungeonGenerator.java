package dungeon.backend.generation;

import java.util.ArrayList;
import java.util.List;

import dungeon.backend.DungeonType;
import dungeon.backend.generation.section.DungeonSection;
import dungeon.backend.generation.section.SectionGenerator;
import dungeon.connectionInterfaces.CellType;
import dungeon.util.physics.Point;

public class DungeonGenerator {
	public final DungeonType targetType;

	public DungeonGenerator(final DungeonType type) {
		targetType = type;
	}

	public DungeonGraph generate() {
		final SectionGenerator g = new SectionGenerator();

		final List<DungeonSection> sections = new ArrayList<>();
		final DungeonGraph generationTarget = new DungeonGraph();

		final DungeonSection startingRoom = g.generateRoom(-2, -2, 3, 7, 3, 7);
		sections.add(startingRoom);
		generationTarget.makeSectionFloor(startingRoom);
		for(int num=0;num<20;num++){
			List<Point> walls=generationTarget.getAllLocationsOfCellType(CellType.Wall);
			List<Point> oneNeighborWalls=new ArrayList<>();
			for (Point p:walls){
				if(generationTarget.numNeighborsOfType((int) p.getX(),(int) p.getY(), CellType.Floor)==1){
					oneNeighborWalls.add(p);
				}
			}
		}
		return generationTarget;
	}
}
