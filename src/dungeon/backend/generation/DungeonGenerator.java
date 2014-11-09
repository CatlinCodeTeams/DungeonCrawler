package dungeon.backend.generation;

import java.util.ArrayList;
import java.util.List;

import dungeon.backend.DungeonType;
import dungeon.backend.generation.section.DungeonSection;
import dungeon.backend.generation.section.SectionGenerator;

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
		// More generation to go here...
		for(int x=0;x<20;x++){

		}
		return generationTarget;
	}
}
