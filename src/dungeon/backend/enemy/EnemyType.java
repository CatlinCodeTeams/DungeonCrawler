package dungeon.backend.enemy;

import dungeon.animation.AnimationInstance;
import dungeon.animation.character.SlimeAnimation;

public enum EnemyType {
	Slime(30,10,SlimeAnimation.moveUp,SlimeAnimation.moveDown,SlimeAnimation.moveLeft,SlimeAnimation.moveRight);
	EnemyType(int health,int damage,AnimationInstance moveUp,AnimationInstance moveDown,AnimationInstance moveLeft,AnimationInstance moveRight){

	}
}
