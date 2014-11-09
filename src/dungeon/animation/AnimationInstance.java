package dungeon.animation;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;


public class AnimationInstance {
	protected Queue<AnimationFrame> animation=new LinkedList<AnimationFrame>();

	protected int waitFor=0;
	protected AnimationFrame currentFrame=null;

	public AnimationInstance(Collection<AnimationFrame> frames){
		animation.addAll(frames);
	}
	public AnimationInstance(AnimationFrame[] frames){
		this(Arrays.asList(frames));
	}
	public AnimationFrame getNextFrame() throws IllegalStateException{
		if (waitFor==0){
			if(animation.isEmpty()) throw new IllegalStateException();
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
