package dungeon.backend.generation.section;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import dungeon.util.Direction;
import dungeon.util.factory.Factory;

public class SectionConfig {

	private Random rgen=new Random();
	private final Map<Factory<DungeonSection>,Integer> map;

	public SectionConfig(Map<Factory<DungeonSection>,Integer> map){
		this.map=map;
	}

	public DungeonSection getSection(){
		double sum=0;
		for(Integer i:map.values()){
			sum+=i;
		}
		final HashMap<Factory<DungeonSection>, Double> probabilities = new HashMap<>();
		for (final Factory<DungeonSection> w : map.keySet()) {
			probabilities.put(w, (double) map.get(w) / (double) sum);
		}
		double sum2 = 0;
		for (final Factory<DungeonSection> w : map.keySet()) {
			sum2+=probabilities.get(w);
			probabilities.put(w, sum2);
		}
		final double choice = rgen.nextDouble();
		for (final Factory<DungeonSection> w : map.keySet()) {
			if (choice < probabilities.get(w)) {
				return w.get();
			}
		}
		throw new RuntimeException("SOMETHING IS TERRIBLY WRONG");
	}
	public interface SectionCreator{
		public DungeonSection getSection(int startX,int startY,Direction d);
	}
}
