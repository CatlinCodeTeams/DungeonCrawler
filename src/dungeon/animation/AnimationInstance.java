package dungeon.animation;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;


public class AnimationInstance {
	private final Queue<AnimationFrame> animation=new LinkedList<AnimationFrame>();

	private int waitFor=0;
	private AnimationFrame currentFrame=null;

	public AnimationInstance(Collection<AnimationFrame> frames){
		animation.addAll(frames);
	}
	public AnimationInstance(AnimationFrame[] frames){
		this(Arrays.asList(frames));
	}
	public AnimationFrame getNextFrame() throws IllegalStateException{
		if(animation.isEmpty()) throw new IllegalStateException();
		if (waitFor==0){
			currentFrame=animation.poll();
			waitFor=currentFrame.duration;
		}
		waitFor--;
		return currentFrame;
	}
	public AnimationInstance copy(){
		return new AnimationInstance(this.animation);
	}
}
