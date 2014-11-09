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
		DungeonGraph generationTarget=new DungeonGraph();

		DungeonSection startingRoom=g.generateRoom(-2,-2,3, 10, 3, 10);
		sections.add(startingRoom);
		generationTarget.makeSectionFloor(startingRoom);
		//More generation to go here...

		return generationTarget;
	}
}
