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
	//public static final AnimationInstance walkUp=AnimationFactory.decreasingAlternation("spr_up_walk1.png", "spr_up_walk2.png", 8, 10, 0 -20);
	public static AnimationInstance walkUp;
		
		static {

		List<AnimationFrame> l=new ArrayList<>();
		boolean frame = false;
		for(int count=0;count<20;count++){
			try {
				if(count%5==0){
					frame = !frame;
				}
				
				if (frame){
					l.add(new AnimationFrame("spr_up_walk1.png",1,0-count*(0),-20-count*(1)+40));
				}
				else{
					l.add(new AnimationFrame("spr_up_walk2.png",1,0-count*(0),-20-count*(1)+40));
				}
				
			} catch (IOException e) {}
		}
		walkUp = new AnimationInstance(l);
	}
	
	
	//public static final AnimationInstance walkDown=AnimationFactory.decreasingAlternation("spr_down_walk1.png", "spr_down_walk2.png", 8, 10, 0, 20);
	public static final AnimationInstance walkDown;
	
	static {

	List<AnimationFrame> l=new ArrayList<>();
	boolean frame = false;
	for(int count=0;count<20;count++){
		try {
			if(count%5==0){
				frame = !frame;
			}
			
			if (frame){
				l.add(new AnimationFrame("spr_down_walk1.png",1,0-count*(0),20-count*(-1)-40));
			}
			else{
				l.add(new AnimationFrame("spr_down_walk2.png",1,0-count*(0),20-count*(-1)-40));
			}
			
		} catch (IOException e) {}
	}
	walkDown = new AnimationInstance(l);
	
	}
	
	public static AnimationInstance walkLeft;
	
	static{
		List<AnimationFrame> l=new ArrayList<>();
		boolean frame = false;
		for(int count=0;count<20;count++){
			try {
				if(count%5==0){
					frame = !frame;
				}
				
				if (frame){
					l.add(new AnimationFrame("spr_left_walk1.png",1,20-count*(1),0-count*(0)));
				}
				else{
					l.add(new AnimationFrame("spr_left_walk2.png",1,20-count*(1),0-count*(0)));
				}
				
			} catch (IOException e) {}
		}
		walkLeft = new AnimationInstance(l);
	}

	public static AnimationInstance walkRight;
	
	static{
		
		List<AnimationFrame> l=new ArrayList<>();
		boolean frame = false;
		for(int count=0;count<20;count++){
			try {
				if(count%5==0){
					frame = !frame;
				}
				
				if (frame){
					l.add(new AnimationFrame("spr_right_walk1.png",1,-20-count*(-1),0-count*(0)));
				}
				else{
					l.add(new AnimationFrame("spr_right_walk2.png",1,-20-count*(-1),0-count*(0)));
				}
				
			} catch (IOException e) {}
		}
		walkRight = new AnimationInstance(l);
	}

}
