package dungeon.backend.player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import dungeon.animation.Animateable;
import dungeon.animation.AnimationFrame;
import dungeon.animation.AnimationInstance;
import dungeon.util.Direction;

public class PlayerManager extends Animateable{
	public int x;
	public int y;
	public Direction currentDirection;

	@Override
	public AnimationFrame getCurrentStaticImage() {
		return staticImage;
	}
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
		for(int y=21;y>1;y--){
			l.add(new AnimationFrame(moving, 20, 0, y));
		}
		walkUp=new AnimationInstance(l);
	}
}
