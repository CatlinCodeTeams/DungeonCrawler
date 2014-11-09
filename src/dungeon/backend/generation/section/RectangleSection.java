package dungeon.backend.generation.section;

import java.util.ArrayList;
import java.util.List;

import dungeon.util.physics.Point;

public class RectangleSection implements DungeonSection{
	public final int startX;
	public final int startY;
	public final int width;
	public final int height;

	public RectangleSection(final int startX, final int startY, final int width, final int height) {
		this.startX = startX;
		this.startY = startY;
		this.width = width;
		this.height = height;
	}

	@Override
	public List<Point> getContents() {
		List<Point> contents=new ArrayList<>();
		for(int x=0;x<width;x++){
			for(int y=0;y<height;y++){
				contents.add(new Point(startX+x,startY+y));
			}
		}
		return contents;
	}
}
