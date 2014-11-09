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
}