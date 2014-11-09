package dungeon.backend.enemy;

import dungeon.animation.AnimationInstance;
import dungeon.animation.character.SlimeAnimation;

public enum EnemyType {
	Slime(30,10,EnemyAi.Simple,SlimeAnimation.still,SlimeAnimation.moveUp,SlimeAnimation.moveDown,SlimeAnimation.moveLeft,SlimeAnimation.moveRight);
	public final int health;
	public final int damage;
	public final EnemyAi ai;
	public final AnimationInstance staticInstance;
	public final AnimationInstance moveUp;
	public final AnimationInstance moveDown;
	public final AnimationInstance moveLeft;
	public final AnimationInstance moveRight;
	EnemyType(int health,int damage,EnemyAi ai,AnimationInstance staticInstance,AnimationInstance moveUp,AnimationInstance moveDown,AnimationInstance moveLeft,AnimationInstance moveRight){
		this.health=health;
		this.damage=damage;
		this.ai=ai;
		this.staticInstance=staticInstance;
		this.moveUp=moveUp;
		this.moveDown=moveDown;
		this.moveLeft=moveLeft;
		this.moveRight=moveRight;
	}
}
