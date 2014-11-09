package dungeon.backend.generation.section;

public class DungeonSection {
	public final int startX;
	public final int startY;
	public final int width;
	public final int height;

	public DungeonSection(final int startX, final int startY, final int width,
			final int height) {
		this.startX = startX;
		this.startY = startY;
		this.width = width;
		this.height = height;
	}
}
