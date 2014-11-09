package dungeon.animation.character;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import dungeon.animation.AnimationFrame;
import dungeon.animation.AnimationInstance;

public class PlayerAnimation {
	//Static methods
	public static final AnimationFrame staticImage;
	static{
		BufferedImage still=new BufferedImage(20,20,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g=still.createGraphics();
		g.setColor(Color.BLUE);
		g.fillOval(0, 0, 20, 20);
		staticImage=new AnimationFrame(still,1);
	}
	public static final AnimationInstance walkUp;
	static{
		List<AnimationFrame> l=new ArrayList<>();
		BufferedImage moving=new BufferedImage(20,20,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g=moving.createGraphics();
		g.setColor(Color.RED);
		g.fillOval(0, 0, 20, 20);
		for(int y=20;y>0;y--){
			l.add(new AnimationFrame(moving, 10, 0, y));
		}
		walkUp=new AnimationInstance(l);
	}
	public static final AnimationInstance walkDown;
	static{
		List<AnimationFrame> l=new ArrayList<>();
		BufferedImage moving=new BufferedImage(20,20,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g=moving.createGraphics();
		g.setColor(Color.RED);
		g.fillOval(0, 0, 20, 20);
		for(int y=20;y>0;y--){
			l.add(new AnimationFrame(moving, 10, 0, -y));
		}
		walkDown=new AnimationInstance(l);
	}
	public static final AnimationInstance walkLeft;
	static{
		BufferedImage moving=new BufferedImage(20,20,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g=moving.createGraphics();
		g.setColor(Color.RED);
		g.fillOval(0, 0, 20, 20);

		List<AnimationFrame> l=new ArrayList<>();
		for(int x=20;x>0;x--){
			l.add(new AnimationFrame(moving, 10, x, 0));
		}
		walkLeft=new AnimationInstance(l);
	}
	public static final AnimationInstance walkRight;
	static{
		BufferedImage moving=new BufferedImage(20,20,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g=moving.createGraphics();
		g.setColor(Color.RED);
		g.fillOval(0, 0, 20, 20);

		List<AnimationFrame> l=new ArrayList<>();
		for(int x=20;x>0;x--){
			l.add(new AnimationFrame(moving, 10, -x, 0));
		}
		walkRight=new AnimationInstance(l);
	}
}
