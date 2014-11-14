package dungeon.backend.enemy;

import java.util.Random;

import dungeon.backend.generation.DungeonGraph;
import dungeon.backend.player.PlayerManager;
import dungeon.util.Direction;

public enum EnemyAi {
	Simple(new AiControler(){
		@Override
		public Direction toMove(Enemy e, DungeonGraph g,EnemyManager em,PlayerManager pm, Random aiRandom) {
//			if(pm.y==e.y){
//			if(pm.x==e.x+1){
//				return Direction.EAST;
//			}else if(pm.x==e.x-1){
//				return Direction.WEST;
//			}
//			}
//			if(pm.x==e.x){
//				if(pm.y==pm.y+1){
//					return Direction.NORTH;
//				}
//				if(pm.y==pm.y+1){
//					return Direction.SOUTH;
//				}
//			}
			switch(aiRandom.nextInt(4)){
			case 0:
				if(g.getCellTypeAt(e.x+1, e.y).PASSABLE && em.getEnemyAt(e.x+1, e.y)==null &&!(pm.x==e.x+1 && pm.y==e.y)){
					return Direction.EAST;
				}
			case 1:
				if(g.getCellTypeAt(e.x-1, e.y).PASSABLE && em.getEnemyAt(e.x-1, e.y)==null &&!(pm.x==e.x-1 && pm.y==e.y)){
					return Direction.WEST;
				}
			case 2:
				if(g.getCellTypeAt(e.x, e.y-1).PASSABLE && em.getEnemyAt(e.x, e.y-1)==null &&!(pm.x==e.x && pm.y==e.y-1)){
					return Direction.NORTH;
				}
			case 3:
				if(g.getCellTypeAt(e.x, e.y+1).PASSABLE && em.getEnemyAt(e.x, e.y+1)==null&&!(pm.x==e.x && pm.y==e.y+1)){
					return Direction.SOUTH;
				}
			}
			if(g.getCellTypeAt(e.x+1, e.y).PASSABLE && em.getEnemyAt(e.x+1, e.y)==null &&!(pm.x==e.x+1 && pm.y==e.y)){
				return Direction.EAST;
			}
			else if(g.getCellTypeAt(e.x-1, e.y).PASSABLE && em.getEnemyAt(e.x-1, e.y)==null &&!(pm.x==e.x-1 && pm.y==e.y)){
				return Direction.WEST;
			}
			else if(g.getCellTypeAt(e.x, e.y-1).PASSABLE && em.getEnemyAt(e.x, e.y-1)==null &&!(pm.x==e.x && pm.y==e.y-1)){
				return Direction.NORTH;
			}
			else if(g.getCellTypeAt(e.x, e.y+1).PASSABLE && em.getEnemyAt(e.x, e.y+1)==null&&!(pm.x==e.x && pm.y==e.y+1)){
				return Direction.SOUTH;
			}
			return null;

		}
	});


	public AiControler control;

	EnemyAi(AiControler ai){
		control=ai;
	}

	public interface AiControler{
		public Direction toMove(Enemy e, DungeonGraph g,EnemyManager em,PlayerManager pm, Random aiRandom);
	}
}
