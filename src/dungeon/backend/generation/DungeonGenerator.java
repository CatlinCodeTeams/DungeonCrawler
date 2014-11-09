package dungeon.backend.generation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dungeon.backend.DungeonType;
import dungeon.backend.generation.section.DungeonSection;
import dungeon.backend.generation.section.RectangleSection;
import dungeon.connectionInterfaces.CellType;
import dungeon.util.physics.Point;

public class DungeonGenerator {
	public final DungeonType targetType;

	public DungeonGenerator(final DungeonType type) {
		targetType = type;
	}

	public DungeonGraph generate() {

		final List<DungeonSection> sections = new ArrayList<>();
		final DungeonGraph generationTarget = new DungeonGraph();
		Random RNG=new Random();
		final int width=3+(int) (4 * Math.abs(RNG.nextGaussian()));
		final int height=3+(int) (4 * Math.abs(RNG.nextGaussian()));
		final DungeonSection startingRoom = new RectangleSection(-2,-2, width, height);
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

			Point select=oneNeighborWalls.get(RNG.nextInt(oneNeighborWalls.size()));
			Point opposite=generationTarget.pointOpositeNeighborOfType((int)select.getX(), (int)select.getY(), CellType.Floor);

			if(select.getX()>opposite.getX()){

			}else if(select.getX()<opposite.getX()){

			}else if(select.getY()>opposite.getY()){

			}else if(select.getY()<opposite.getY()){

			}
		}
		return generationTarget;
	}
}
