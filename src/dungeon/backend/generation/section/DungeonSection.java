package dungeon.backend.generation.section;

public class DungeonSection {
	public final int startX;
	public final int startY;
	public final int width;
	public final int height;

	public DungeonSection(int startX,int startY,int width,int height){
		this.startX=startX;
		this.startY=startY;
		this.width=width;
		this.height=height;
	}
}
