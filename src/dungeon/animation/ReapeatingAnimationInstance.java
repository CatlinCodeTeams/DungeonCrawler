package dungeon.animation;

import java.util.Arrays;
import java.util.Collection;

public class ReapeatingAnimationInstance extends AnimationInstance{

	protected AnimationInstance self;

	public ReapeatingAnimationInstance(AnimationFrame[] frames){
		this(Arrays.asList(frames));
	}
	public ReapeatingAnimationInstance(Collection<AnimationFrame> frames){
		super(frames);
		self=this.copy();
	}
	public AnimationFrame getNextFrame(){
		try{
			return super.getNextFrame();
		}catch(IllegalStateException e){
			this.animation=self.copy().animation;
			return getNextFrame();
		}
	}
}
