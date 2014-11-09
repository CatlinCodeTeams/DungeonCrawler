package dungeon.animation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnimationFactory {
	public static AnimationInstance decreasingAlternation(String file1,String file2,int duration,int numFrames,int downFromX,int downFromY){
		List<AnimationFrame> l=new ArrayList<>();
		for(int count=0;count<numFrames;count++){
			try {
				if(count%2==0){
					l.add(new AnimationFrame(file1,duration,downFromX-count*(downFromX/numFrames),downFromY-count*(downFromY/numFrames)));
				}else{
					l.add(new AnimationFrame(file2,duration,downFromX-count*(downFromX/numFrames),downFromY-count*(downFromY/numFrames)));
				}
			} catch (IOException e) {}
		}
		return new AnimationInstance(l);
	}
	
	public static AnimationInstance smoothDA(String file1,String file2,int duration,int numFrames,int startX,int startY, int xCount, int yCount){

		List<AnimationFrame> l=new ArrayList<>();
		boolean frame = false;
		for(int count=0;count<20;count++){
			try {
				if(count%5==0){
					frame = !frame;
				}
				
				if (frame){
					l.add(new AnimationFrame(file1,1,startX-count*(xCount),startY-count*(yCount)+40));
				}
				else{
					l.add(new AnimationFrame(file2,1,startX-count*(xCount),startY-count*(yCount)+40));
				}
				
			} catch (IOException e) {}
		}
		return new AnimationInstance(l);
	}
}