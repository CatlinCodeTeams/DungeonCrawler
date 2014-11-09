package dungeon.backend.player;

import dungeon.animation.Animateable;
import dungeon.animation.AnimationFrame;
import dungeon.animation.character.PlayerAnimation;
import dungeon.util.Direction;

public class PlayerManager extends Animateable{
	public int x;
	public int y;
	public Direction currentDirection;

	@Override
	public AnimationFrame getCurrentStaticImage() {
		return PlayerAnimation.staticImage;
	}
}
