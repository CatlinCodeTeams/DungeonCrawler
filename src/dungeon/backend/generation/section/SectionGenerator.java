package dungeon.backend.generation.section;

import java.util.Random;

public class SectionGenerator {

	public final Random RNG=new Random();

	public DungeonSection generateRoom(int widthMin,int widthMax,int heightMin,int heightMax){
		int width=widthMin+(int)((widthMax-widthMin)*Math.abs(RNG.nextGaussian()));
		int height=heightMin+(int)((heightMax-heightMin)*Math.abs(RNG.nextGaussian()));
		return new DungeonSection(width,height);
	}
}
