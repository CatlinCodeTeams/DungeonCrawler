package dungeon.backend.generation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dungeon.backend.DungeonType;
import dungeon.backend.generation.section.DungeonSection;
import dungeon.backend.generation.section.RectangleSection;
import dungeon.connectionInterfaces.CellType;
import dungeon.util.Direction;
import dungeon.util.physics.Point;

public class DungeonGenerator {
	public final DungeonType targetType;

	public DungeonGenerator(final DungeonType type) {
		targetType = type;
	}

	public DungeonGraph generate() {
		//Make the graph and the RNG
		final DungeonGraph generationTarget = new DungeonGraph();
		Random RNG=new Random();
		//Generate the first rooms size
		final int width=3+(int) (4 * Math.abs(RNG.nextGaussian()));
		final int height=3+(int) (4 * Math.abs(RNG.nextGaussian()));
		//Now make the first room, of that size
		generationTarget.makeSectionFloor(new RectangleSection(-2,-2, width, height));
		//Generate remaining rooms
		for(int num=0;num<20;num++){
			while(true){
				//Select walls with one free neighbor
				List<Point> oneNeighborWalls=new ArrayList<>();
				for (Point p:generationTarget.getAllLocationsOfCellType(CellType.Wall)){
					if(generationTarget.numNeighborsOfType((int) p.getX(),(int) p.getY(), CellType.Floor)==1){
						oneNeighborWalls.add(p);
					}
				}
				//Choose a random on of those walls
				Point select=oneNeighborWalls.get(RNG.nextInt(oneNeighborWalls.size()));
				//Get the opposite of its freespace to help the generation code
				Point opposite=generationTarget.pointOpositeNeighborOfType((int)select.getX(), (int)select.getY(), CellType.Floor);

				DungeonSection toGenSection;
				if(select.getX()>opposite.getX()){
					toGenSection=targetType.config.getSection((int)opposite.getX(), (int)opposite.getY(), Direction.WEST);
				}else if(select.getX()<opposite.getX()){
					toGenSection=targetType.config.getSection((int)opposite.getX(), (int)opposite.getY(), Direction.EAST);
				}else if(select.getY()>opposite.getY()){
					toGenSection=targetType.config.getSection((int)opposite.getX(), (int)opposite.getY(), Direction.NORTH);
				}else if(select.getY()<opposite.getY()){
					toGenSection=targetType.config.getSection((int)opposite.getX(), (int)opposite.getY(), Direction.SOUTH);
				}else{
					throw new RuntimeException();
				}
				if(generationTarget.verifyFree(toGenSection)){
					generationTarget.makeSectionFloor(toGenSection);
					generationTarget.makeCellType((int)select.getX(), (int)select.getY(),CellType.ClosedDoor);
					break;
				}else{
					System.out.println(select.getX());
					continue;
				}
			}
		}
		return generationTarget;
	}
}
