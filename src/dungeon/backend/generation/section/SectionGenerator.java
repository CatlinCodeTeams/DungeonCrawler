package dungeon.backend.generation.section;

import java.util.Random;

public class SectionGenerator {

	public final Random RNG = new Random();

	public DungeonSection generateRoom(final int startX, final int startY,
			final int widthMin, final int widthMax, final int heightMin,
			final int heightMax) {
		final int width = widthMin
				+ (int) ((widthMax - widthMin) * Math.abs(RNG.nextGaussian()));
		final int height = heightMin
				+ (int) ((heightMax - heightMin) * Math.abs(RNG.nextGaussian()));
		return new DungeonSection(startX, startY, width, height);
	}
}
