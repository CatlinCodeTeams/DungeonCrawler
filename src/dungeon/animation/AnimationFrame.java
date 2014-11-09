package dungeon.animation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
	public AnimationFrame(String imageName,int duration,int xDisplacment,int yDisplacment) throws IOException{
		this(ImageIO.read(new File("assets\\"+imageName)),duration,xDisplacment,yDisplacment);
	}
	
	public AnimationFrame(BufferedImage image,int duration){
		this(image,duration,0,0);
	}
	@Override
	public String toString(){
		return duration+":"+xDisplacment+":"+yDisplacment;
	}
}
