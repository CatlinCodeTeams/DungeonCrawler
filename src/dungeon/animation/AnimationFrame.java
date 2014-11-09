package dungeon.animation;

import java.awt.image.BufferedImage;

public class AnimationFrame {
	public final BufferedImage image;
	public final int duration;
	public final int xDisplacment;
	public final int yDisplacment;
	public AnimationFrame(BufferedImage image,int duration,int xDisplacment,int yDisplacment){
		this.image=image;
		this.duration=duration;
		this.xDisplacment=xDisplacment;
		this.yDisplacment=yDisplacment;
	}
	public AnimationFrame(BufferedImage image,int duration){
		this(image,duration,0,0);
	}
}
