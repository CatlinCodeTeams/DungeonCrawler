package dungeon.animation;

import java.util.Collection;

public class ReapeatingAnimationInstance extends AnimationInstance{
	protected AnimationInstance self;
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
