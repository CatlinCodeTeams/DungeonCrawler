package dungeon.backend.enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import dungeon.animation.Animateable;
import dungeon.backend.generation.DungeonGraph;
import dungeon.backend.player.PlayerManager;
import dungeon.connectionInterfaces.CellType;
import dungeon.levelConfig.DungeonType;
import dungeon.util.Direction;
import dungeon.util.physics.Point;

public class EnemyManager {
	List<Enemy> currentEnemies=new ArrayList<>();
	public AtomicBoolean enemyTurn=new AtomicBoolean(false);
	public final DungeonGraph world;
	public final DungeonType type;
	public final PlayerManager player;
	public EnemyManager(DungeonGraph world,DungeonType type,PlayerManager playMan){
		this.world=world;
		this.type=type;
		this.player=playMan;
	}
	public void takeTurn(){
		Random aiRandom=new Random();
		if(currentEnemies.size()<10){
			List<Point> spawnPoints=world.getAllLocationsOfCellType(CellType.Floor);
			List<Point> fairSpawPoints=new ArrayList<>();
			for(Point p:spawnPoints){
				if(p.getX()>(player.x+20)||p.getX()<(player.x-20)||p.getY()>(player.y+20)||p.getY()<(player.y-20)){
					fairSpawPoints.add(p);
				}
			}
			Point selected=fairSpawPoints.get(aiRandom.nextInt(fairSpawPoints.size()));
			currentEnemies.add(new Enemy(EnemyType.Slime,(int)selected.getX(),(int)selected.getY()));
		}
		for(Enemy e:currentEnemies){
			Direction move=e.type.ai.control.toMove(e, world, aiRandom);
			if (move==null){
				continue;
			}
			switch(move){
			case NORTH:
				e.y--;
				e.overrideAnimationInstance(e.type.moveUp.copy());
				break;
			case SOUTH:
				e.y++;
				e.overrideAnimationInstance(e.type.moveDown.copy());
				break;
			case WEST:
				e.x--;
				e.overrideAnimationInstance(e.type.moveLeft.copy());
				break;
			case EAST:
				e.x++;
				e.overrideAnimationInstance(e.type.moveRight.copy());
				break;
			}
		}
	}

	public Enemy getEnemyAt(int x,int y){
		for(Enemy e:currentEnemies){
			if(e.x==x && e.y==y){
				return e;
			}
		}
		return null;
	}

	public void queueForTurn(final Animateable wait){
		enemyTurn.set(true);
		new Thread(new Runnable(){
			@Override
			public void run() {
				while(wait.isAnimating()){
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
				takeTurn();
			}
		}).start();
	}
}
