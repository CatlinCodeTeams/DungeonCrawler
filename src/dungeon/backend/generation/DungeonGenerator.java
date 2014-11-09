package dungeon.backend.generation;

import java.util.ArrayList;
import java.util.List;

import dungeon.backend.DungeonType;
import dungeon.backend.generation.section.DungeonSection;
import dungeon.backend.generation.section.SectionGenerator;

public class DungeonGenerator {
	public final DungeonType targetType;
	public DungeonGenerator(DungeonType type){
		targetType=type;
	}
	public DungeonGraph generate() {
		SectionGenerator g=new SectionGenerator();
		List<DungeonSection> sections=new ArrayList<>();
		sections.add(g.generateRoom(3, 10, 3, 10));
		//More generation to go here...
		DungeonGraph generationTarget=new DungeonGraph();
		for (DungeonSection s:sections){
			int startX=-2;
			int startY=-2;
			for(int x=0;x<s.width;x++){
				for(int y=0;y<s.height;y++){
					generationTarget.makeCellFloor(startX+x, startY+y);
				}
			}
		}
		return generationTarget;
	}
}
