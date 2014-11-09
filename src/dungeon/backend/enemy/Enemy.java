package dungeon.backend.enemy;

import dungeon.animation.Animateable;
import dungeon.animation.AnimationFrame;

public class Enemy extends Animateable{
	public int x;
	public int y;
	public EnemyType type;
	public Enemy(EnemyType type,int startX,int startY){
		x=startX;
		y=startY;
		this.type=type;
	}

	@Override
	public AnimationFrame getCurrentStaticImage() {
		return type.staticInstance.getNextFrame();
	}

}
