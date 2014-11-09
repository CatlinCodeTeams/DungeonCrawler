package dungeon.levelConfig;

import java.util.Random;

import dungeon.backend.generation.section.DungeonSection;
import dungeon.backend.generation.section.RectangleSection;
import dungeon.connectionInterfaces.CellType;
import dungeon.levelConfig.ItemConfig.Item;
import dungeon.levelConfig.ItemConfig.ItemType;
import dungeon.levelConfig.SectionConfig.SectionCreator;
import dungeon.util.Direction;

public enum DungeonType {
	BASEMENT(new SectionConfig(new SectionCreator[]{
			//Cooridors
			new SectionCreator(60){
				@Override
				public DungeonSection getSection(int startX, int startY, Direction d) {
					int legnth=(int)(20*Math.abs(rgen.nextGaussian()))+3;
					if(d==Direction.NORTH){
						return new RectangleSection(startX,startY-legnth+1,1,legnth);
					}
					if(d==Direction.SOUTH){
						return new RectangleSection(startX,startY,1,legnth);
					}
					if(d==Direction.WEST){
						return new RectangleSection(startX-legnth+1,startY,legnth,1);
					}
					if(d==Direction.EAST){
						return new RectangleSection(startX,startY,legnth,1);
					}
					throw new RuntimeException();
				}

			},
			//Room
			new SectionCreator(40){
				@Override
				public DungeonSection getSection(int startX, int startY, Direction d) {
					int height=(int)(5*rgen.nextDouble())+3;
					int width=(int)(5*rgen.nextDouble())+3;
					if(d==Direction.NORTH){
						return new RectangleSection(startX-width/2,startY-height+1,width,height);
					}
					if(d==Direction.SOUTH){
						return new RectangleSection(startX-width/2,startY,width,height);
					}
					if(d==Direction.WEST){
						return new RectangleSection(startX-width+1,startY-height/2,width,height);
					}
					if(d==Direction.EAST){
						return new RectangleSection(startX,startY-height/2,width,height);
					}
					throw new RuntimeException();
				}

			},
	}),new ItemConfig(new Item[]{new Item(CellType.Stair,ItemType.SET_NUM,1),new Item(CellType.Torch,ItemType.CHANCE,0.01)}));

	public static final Random rgen=new Random();
	public final SectionConfig selectionConfig;
	public final ItemConfig items;

	DungeonType(SectionConfig config,ItemConfig i) {
		selectionConfig=config;
		items=i;
	}

	public String getName() {
		return name();
	}

}
