package dungeon.backend.enemy;

import java.util.Random;

import dungeon.backend.generation.DungeonGraph;
import dungeon.util.Direction;

public enum EnemyAi {
	Simple(new AiControler(){
		@Override
		public Direction toMove(Enemy e, DungeonGraph g, Random aiRandom) {
			switch(aiRandom.nextInt(4)){
			case 0:
				if(g.getCellTypeAt(e.x+1, e.y).PASSABLE){
					return Direction.EAST;
				}else if(g.getCellTypeAt(e.x-1, e.y).PASSABLE){
					return Direction.WEST;
				}
				break;
			case 1:
				if(g.getCellTypeAt(e.x-1, e.y).PASSABLE){
					return Direction.WEST;
				}else if(g.getCellTypeAt(e.x+1, e.y).PASSABLE){
					return Direction.EAST;
				}
				break;
			case 2:
				if(g.getCellTypeAt(e.x, e.y+1).PASSABLE){
					return Direction.NORTH;
				}else if(g.getCellTypeAt(e.x, e.y-1).PASSABLE){
					return Direction.SOUTH;
				}else if(g.getCellTypeAt(e.x+1, e.y).PASSABLE){
					return Direction.EAST;
				}else if(g.getCellTypeAt(e.x-1, e.y).PASSABLE){
					return Direction.WEST;
				}
				break;
			case 3:
				if(g.getCellTypeAt(e.x, e.y-1).PASSABLE){
					return Direction.SOUTH;
				}else if(g.getCellTypeAt(e.x, e.y+1).PASSABLE){
					return Direction.NORTH;
				}else if(g.getCellTypeAt(e.x-1, e.y).PASSABLE){
					return Direction.WEST;
				}else if(g.getCellTypeAt(e.x+1, e.y).PASSABLE){
					return Direction.EAST;
				}
				break;
			}
			return null;
		}
	});


	public AiControler control;

	EnemyAi(AiControler ai){
		control=ai;
	}

	public interface AiControler{
		public Direction toMove(Enemy e, DungeonGraph g, Random aiRandom);
	}
}
