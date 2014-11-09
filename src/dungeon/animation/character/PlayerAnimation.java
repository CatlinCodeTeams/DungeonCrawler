package dungeon.animation.character;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dungeon.animation.AnimationFactory;
import dungeon.animation.AnimationFrame;
import dungeon.animation.AnimationInstance;

public class PlayerAnimation {
	public static final AnimationInstance walkUp=AnimationFactory.decreasingAlternation("spr_up_walk1.png", "spr_up_walk2.png", 2, 20, 0, 20);
		
	public static final AnimationInstance walkDown=AnimationFactory.decreasingAlternation("spr_down_walk1.png", "spr_down_walk2.png", 2, 20, 0, -20);
			
	public static final AnimationInstance walkLeft=AnimationFactory.decreasingAlternation("spr_left_walk1.png", "spr_left_walk2.png", 2, 20, 20, 0);

	public static final AnimationInstance walkRight=AnimationFactory.decreasingAlternation("spr_right_walk1.png", "spr_right_walk2.png", 2, 20, -20, 0);

}
