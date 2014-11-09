package dungeon.animation.character;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dungeon.animation.AnimationFrame;
import dungeon.animation.AnimationInstance;

public class PlayerAnimation {
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

			} catch (IOException e) {
			}
		}
		walkRight = new AnimationInstance(l);
	}

	public static AnimationInstance attackUp;
	static{
		try {
			attackUp=new AnimationInstance(new AnimationFrame[]{new AnimationFrame("spr_sword_up.png",30,0,-10)});
		} catch (IOException e) {}
	}
	public static AnimationInstance attackDown;
	static{
		try {
			attackDown=new AnimationInstance(new AnimationFrame[]{new AnimationFrame("spr_sword_down.png",30,0,0)});
		} catch (IOException e) {}
	}
	public static AnimationInstance attackRight;
	static{
		try {
			attackRight=new AnimationInstance(new AnimationFrame[]{new AnimationFrame("spr_sword_right.png",30,0,0)});
		} catch (IOException e) {}
	}
	public static AnimationInstance attackLeft;
	static{
		try {
			attackLeft=new AnimationInstance(new AnimationFrame[]{new AnimationFrame("spr_sword_left.png",30,-10,0)});
		} catch (IOException e) {}
	}

}
