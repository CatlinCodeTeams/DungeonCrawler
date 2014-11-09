package dungeon.animation.character;

import java.io.IOException;

import dungeon.animation.AnimationFrame;
import dungeon.animation.AnimationInstance;
import dungeon.animation.ReapeatingAnimationInstance;

public class SlimeAnimation {
	public static AnimationInstance moveUp;
	static {
		try {
			moveUp = new AnimationInstance(new AnimationFrame[] {
					new AnimationFrame("Slime0.png", 10, 20, 0),
					new AnimationFrame("Slime1.png", 10, 16, 0),
					new AnimationFrame("Slime2.png", 10, 12, 0),
					new AnimationFrame("Slime3.png", 10, 8, 0),
					new AnimationFrame("Slime4.png", 10, 4, 0),
					new AnimationFrame("Slime5.png", 10, 0, 0) });
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public static AnimationInstance moveDown;
	static {
		try {
			moveDown = new AnimationInstance(new AnimationFrame[] {
					new AnimationFrame("Slime0.png", 10, -20, 0),
					new AnimationFrame("Slime1.png", 10, -16, 0),
					new AnimationFrame("Slime2.png", 10, -12, 0),
					new AnimationFrame("Slime3.png", 10, -8, 0),
					new AnimationFrame("Slime4.png", 10, -4, 0),
					new AnimationFrame("Slime5.png", 10, 0, 0) });
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public static AnimationInstance moveRight;
	static {
		try {
			moveRight = new AnimationInstance(new AnimationFrame[] {
					new AnimationFrame("Slime0.png", 10, 0, -20),
					new AnimationFrame("Slime1.png", 10, 0, -16),
					new AnimationFrame("Slime2.png", 10, 0, -12),
					new AnimationFrame("Slime3.png", 10, 0, -8),
					new AnimationFrame("Slime4.png", 10, 0, -4),
					new AnimationFrame("Slime5.png", 10, 0, 0) });
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public static AnimationInstance moveLeft;
	static {
		try {
			moveLeft = new AnimationInstance(new AnimationFrame[] {
					new AnimationFrame("Slime0.png", 10, 0, 20),
					new AnimationFrame("Slime1.png", 10, 0, 16),
					new AnimationFrame("Slime2.png", 10, 0, 12),
					new AnimationFrame("Slime3.png", 10, 0, 8),
					new AnimationFrame("Slime4.png", 10, 0, 4),
					new AnimationFrame("Slime5.png", 10, 0, 0) });
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public static AnimationInstance still;
	static{
		try{
		new ReapeatingAnimationInstance(new AnimationFrame[]{new AnimationFrame("Slime1.png", 10, 0, 0)});
		}catch (IOException e){
			throw new RuntimeException(e);
		}
	}
}