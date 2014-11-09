package dungeon.animation;


public abstract class Animateable {
	private AnimationInstance currentAnimation=null;

	public void tryAddAnimationInstance(AnimationInstance a){
		if (currentAnimation==null){
			currentAnimation=a;
		}
	}

	public AnimationFrame getNextImage(){
		if (currentAnimation==null){
			return getCurrentStaticImage();
		}
		try{
			return currentAnimation.getNextFrame();
		}catch(IllegalStateException e){
			currentAnimation=null;
			return getCurrentStaticImage();
		}
	}

	public abstract AnimationFrame getCurrentStaticImage();
}
