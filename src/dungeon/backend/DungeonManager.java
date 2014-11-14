package dungeon.backend;

import dungeon.animation.Animateable;
import dungeon.animation.character.PlayerAnimation;
import dungeon.backend.enemy.Enemy;
import dungeon.backend.enemy.EnemyManager;
import dungeon.backend.generation.DungeonGenerator;
import dungeon.backend.generation.DungeonGraph;
import dungeon.backend.player.PlayerManager;
import dungeon.connectionInterfaces.CellType;
import dungeon.connectionInterfaces.DungeonManagerInterface;
import dungeon.levelConfig.DungeonType;
import dungeon.util.Direction;

public class DungeonManager implements DungeonManagerInterface {

	protected final PlayerManager playerManager = new PlayerManager();
	protected final DungeonGenerator gen;
	protected final EnemyManager enemyMan;
	protected DungeonGraph layout;

	public DungeonManager(final DungeonType type) {
		gen = new DungeonGenerator(type);
		layout = gen.generate();
		enemyMan=new EnemyManager(layout, type, playerManager);
	}

	@Override
	public CellType getCellTypeAt(final int x, final int y) {
		return layout.getCellTypeAt(x, y);
	}

	@Override
	public int getPlayerX() {
		return playerManager.x;
	}

	@Override
	public int getPlayerY() {
		return playerManager.y;
	}

	@Override
	public int getCurrentPlayerHealth() {
		return playerManager.health;
	}

	@Override
	public Animateable getPlayerAnimations() {
		return playerManager;
	}

	@Override
	public void upKeyPressed() {
		if(!playerManager.isAnimating() && !enemyMan.enemyTurn.get()){
			if(layout.getCellTypeAt(getPlayerX(), getPlayerY()-1).PASSABLE && enemyMan.getEnemyAt(getPlayerX(),getPlayerY()-1)==null){
				playerManager.tryAddAnimationInstance(PlayerAnimation.walkUp.copy());
				playerManager.y--;
				enemyMan.queueForTurn(playerManager);
			}
			playerManager.currentDirection=Direction.NORTH;
		}
	}

	@Override
	public void downKeyPressed() {
		if(!playerManager.isAnimating() && !enemyMan.enemyTurn.get()){
			if(layout.getCellTypeAt(getPlayerX(), getPlayerY()+1).PASSABLE && enemyMan.getEnemyAt(getPlayerX(),getPlayerY()+1)==null){
				playerManager.tryAddAnimationInstance(PlayerAnimation.walkDown.copy());
				playerManager.y++;
				enemyMan.queueForTurn(playerManager);			}
			playerManager.currentDirection=Direction.SOUTH;
		}
	}

	@Override
	public void leftKeyPressed() {
		if(!playerManager.isAnimating() && !enemyMan.enemyTurn.get()){
			if(layout.getCellTypeAt(getPlayerX()-1, getPlayerY()).PASSABLE && enemyMan.getEnemyAt(getPlayerX()-1,getPlayerY())==null){
				playerManager.tryAddAnimationInstance(PlayerAnimation.walkLeft.copy());
				playerManager.x--;
				enemyMan.queueForTurn(playerManager);
			}
			playerManager.currentDirection=Direction.WEST;
		}
	}

	@Override
	public void rightKeyPressed() {
		if(!playerManager.isAnimating() && !enemyMan.enemyTurn.get()){
			if(layout.getCellTypeAt(getPlayerX()+1, getPlayerY()).PASSABLE && enemyMan.getEnemyAt(getPlayerX()+1,getPlayerY())==null){
				playerManager.tryAddAnimationInstance(PlayerAnimation.walkRight.copy());
				playerManager.x++;
				enemyMan.queueForTurn(playerManager);
			}
			playerManager.currentDirection=Direction.EAST;
		}
	}

	@Override
	public void interactKeyPressed() {
		if(!playerManager.isAnimating() && !enemyMan.enemyTurn.get()){
			if(playerManager.currentDirection==Direction.NORTH){
				if(enemyMan.getEnemyAt(getPlayerX(), getPlayerY()-1)!=null){
					playerManager.tryAddAnimationInstance(PlayerAnimation.attackUp.copy());
					enemyMan.getEnemyAt(getPlayerX(),getPlayerY()-1).health-=10;
				}else
				layout.makeCellType(getPlayerX(), getPlayerY()-1,layout.getCellTypeAt(getPlayerX(), getPlayerY()-1).transform);
			}
			if(playerManager.currentDirection==Direction.SOUTH){
				if(enemyMan.getEnemyAt(getPlayerX(), getPlayerY()+1)!=null){
					playerManager.tryAddAnimationInstance(PlayerAnimation.attackDown.copy());
					enemyMan.getEnemyAt(getPlayerX(),getPlayerY()+1).health-=10;

				}else
				layout.makeCellType(getPlayerX(), getPlayerY()+1,layout.getCellTypeAt(getPlayerX(), getPlayerY()+1).transform);
			}
			if(playerManager.currentDirection==Direction.EAST){
				if(enemyMan.getEnemyAt(getPlayerX()+1, getPlayerY())!=null){
					playerManager.tryAddAnimationInstance(PlayerAnimation.attackRight.copy());
					enemyMan.getEnemyAt(getPlayerX()+1,getPlayerY()).health-=10;
				}else
				layout.makeCellType(getPlayerX()+1, getPlayerY(),layout.getCellTypeAt(getPlayerX()+1, getPlayerY()).transform);
			}
			if(playerManager.currentDirection==Direction.WEST){
				if(enemyMan.getEnemyAt(getPlayerX()-1, getPlayerY())!=null){
					playerManager.tryAddAnimationInstance(PlayerAnimation.attackLeft.copy());
					enemyMan.getEnemyAt(getPlayerX()-1,getPlayerY()).health-=10;
				}else
				layout.makeCellType(getPlayerX()-1, getPlayerY(),layout.getCellTypeAt(getPlayerX()-1, getPlayerY()).transform);
			}
			if(layout.getCellTypeAt(getPlayerX(), getPlayerY())==CellType.Stair){
				this.resetKeyPressed();
				return;
			}
			enemyMan.queueForTurn(playerManager);
		}

	}

	@Override
	public void resetKeyPressed() {
		if(!playerManager.isAnimating() && !enemyMan.enemyTurn.get()){
			layout = gen.generate();
			playerManager.x = 0;
			playerManager.y = 0;
			enemyMan.currentEnemies.clear();
		}
	}

	@Override
	public Enemy getEnemyAt(int x, int y) {
		return enemyMan.getEnemyAt(x, y);
	}

	@Override
	public void confirmNoVisableEnemyAnimations() {
		enemyMan.enemyTurn.set(false);
	}
}
