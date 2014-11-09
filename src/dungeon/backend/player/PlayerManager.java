package dungeon.backend.player;

import java.io.IOException;

import dungeon.animation.Animateable;
import dungeon.animation.AnimationFrame;
import dungeon.util.Direction;

public class PlayerManager extends Animateable{
	public int x;
	public int y;
	public Direction currentDirection=Direction.SOUTH;

	@Override
	public AnimationFrame getCurrentStaticImage() {
		if(currentDirection==Direction.NORTH){
			try {
				return new AnimationFrame("spr_up.png", 0, 0, 0);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}else if(currentDirection==Direction.SOUTH){
			try {
				return new AnimationFrame("spr_down.png",0,0,0);
			} catch(IOException e) {
				throw new RuntimeException(e);
			}
		}else if(currentDirection==Direction.EAST){
			try {
				return new AnimationFrame("spr_right.png",0,0,0);
			} catch(IOException e) {
				throw new RuntimeException(e);
			}
		}else if(currentDirection==Direction.SOUTH){
			try {
				return new AnimationFrame("spr_left.png",0,0,0);
			} catch(IOException e) {
				throw new RuntimeException(e);
			}
		}else{
			throw new RuntimeException();
		}
	}
}
