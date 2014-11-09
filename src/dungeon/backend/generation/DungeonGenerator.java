package dungeon.backend.generation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dungeon.backend.generation.section.DungeonSection;
import dungeon.backend.generation.section.RectangleSection;
import dungeon.connectionInterfaces.CellType;
import dungeon.levelConfig.DungeonType;
import dungeon.levelConfig.ItemConfig.Item;
import dungeon.levelConfig.ItemConfig.ItemType;
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
					toGenSection=targetType.selectionConfig.getSection((int)opposite.getX(), (int)opposite.getY(), Direction.WEST);
				}else if(select.getX()<opposite.getX()){
					toGenSection=targetType.selectionConfig.getSection((int)opposite.getX(), (int)opposite.getY(), Direction.EAST);
				}else if(select.getY()>opposite.getY()){
					toGenSection=targetType.selectionConfig.getSection((int)opposite.getX(), (int)opposite.getY(), Direction.NORTH);
				}else if(select.getY()<opposite.getY()){
					toGenSection=targetType.selectionConfig.getSection((int)opposite.getX(), (int)opposite.getY(), Direction.SOUTH);
				}else{
					throw new RuntimeException();
				}
				if(generationTarget.verifyFree(toGenSection)){
					generationTarget.makeSectionFloor(toGenSection);
					if(RNG.nextBoolean())
						generationTarget.makeCellType((int)select.getX(), (int)select.getY(),CellType.ClosedDoor);
					else
						generationTarget.makeCellType((int)select.getX(), (int)select.getY(),CellType.Floor);
					break;
				}else{
					continue;
				}
			}
		}
		//Place items throughout the rooms
		for(Item i:targetType.items.items){
			List<Point> validLocations=new ArrayList<>();
			for (Point p:generationTarget.getAllLocationsOfCellType(CellType.Floor)){
				if(generationTarget.numNeighborsOfType((int) p.getX(),(int) p.getY(), CellType.Floor)==4){
					validLocations.add(p);
				}
			}
			if(i.type==ItemType.SET_NUM){
				for(int count=0;count<i.value;count++){
					Point target=(validLocations.get(RNG.nextInt(validLocations.size())));
					generationTarget.makeCellType((int) target.getX(),(int) target.getY(),i.target);
				}
				continue;
			}
			if(i.type==ItemType.CHANCE){
				for(Point p:validLocations){
					if(RNG.nextDouble()<i.value){
						generationTarget.makeCellType((int) p.getX(),(int) p.getY(),i.target);
					}
				}
			}
		}
		return generationTarget;
	}
}
