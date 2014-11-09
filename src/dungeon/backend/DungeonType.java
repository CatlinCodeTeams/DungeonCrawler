package dungeon.backend;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import dungeon.backend.generation.section.DungeonSection;
import dungeon.backend.generation.section.RectangleSection;
import dungeon.backend.generation.section.config.SectionConfig;
import dungeon.backend.generation.section.config.SectionConfig.SectionCreator;
import dungeon.util.Direction;

public enum DungeonType {
	BASEMENT(new SectionCreatorWrapper[]{
			//Cooridors
			new SectionCreatorWrapper(60,new SectionCreator(){
				@Override
				public DungeonSection getSection(int startX, int startY, Direction d) {
					int legnth=(int)(10*rgen.nextDouble())+1;
					if(d==Direction.NORTH){
						return new RectangleSection(startX,startY-legnth,1,legnth);
					}
					if(d==Direction.SOUTH){
						return new RectangleSection(startX,startY,1,legnth);
					}
					if(d==Direction.WEST){
						return new RectangleSection(startX-legnth,startY,legnth,1);
					}
					if(d==Direction.EAST){
						return new RectangleSection(startX,startY,legnth,1);
					}
					throw new RuntimeException();
				}

			}),
			//Room
			new SectionCreatorWrapper(40,new SectionCreator(){

				@Override
				public DungeonSection getSection(int startX, int startY, Direction d) {
					int height=(int)(10*rgen.nextDouble())+1;
					int width=(int)(10*rgen.nextDouble())+1;
					if(d==Direction.NORTH){
						return new RectangleSection(startX-width/2,startY-height,width,height);
					}
					if(d==Direction.SOUTH){
						return new RectangleSection(startX-width/2,startY,1,height);
					}
					if(d==Direction.WEST){
						return new RectangleSection(startX-width,startY-height/2,width,1);
					}
					if(d==Direction.EAST){
						return new RectangleSection(startX,startY-height/2,width,1);
					}
					throw new RuntimeException();
				}

			})
	});
	public static final Random rgen=new Random();
	public final SectionConfig config;

	DungeonType(SectionCreatorWrapper[] wrappers) {
		Map<SectionCreator,Integer> sections=new HashMap<>();
		for(SectionCreatorWrapper w:wrappers){
			sections.put(w.c, w.probability);
		}
		config=new SectionConfig(sections);
	}

	public String getName() {
		return name();
	}

	public static class SectionCreatorWrapper{
		public final SectionCreator c;
		public final int probability;
		public SectionCreatorWrapper(int probability,SectionCreator c){
			this.probability=probability;
			this.c=c;
		}
	}
}
