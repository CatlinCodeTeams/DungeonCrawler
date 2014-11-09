package dungeon.backend.enemy;

import dungeon.animation.AnimationInstance;

public enum EnemyType {
	Slime(30,10,null,null,null,null);
	EnemyType(int health,int damage,AnimationInstance moveUp,AnimationInstance moveDown,AnimationInstance moveLeft,AnimationInstance moveRight){

	}
}
