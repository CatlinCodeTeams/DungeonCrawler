package dungeon.levelConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import dungeon.backend.generation.section.DungeonSection;
import dungeon.util.Direction;

public class SectionConfig {

	private Random rgen=new Random();
	private final Map<SectionCreator,Integer> map;

	public SectionConfig(SectionCreator[] creators){
		Map<SectionCreator,Integer> map=new HashMap<>();
		for(SectionCreator creator:creators){
			map.put(creator, creator.probability);
		}
		this.map=map;
	}
	public SectionConfig(Map<SectionCreator,Integer> map){
		this.map=map;
	}

	public DungeonSection getSection(int startX,int startY,Direction d){
		double sum=0;
		for(Integer i:map.values()){
			sum+=i;
		}
		final HashMap<SectionCreator, Double> probabilities = new HashMap<>();
		for (final SectionCreator w : map.keySet()) {
			probabilities.put(w, (double) map.get(w) / (double) sum);
		}
		double sum2 = 0;
		for (final SectionCreator w : map.keySet()) {
			sum2+=probabilities.get(w);
			probabilities.put(w, sum2);
		}
		final double choice = rgen.nextDouble();
		for (final SectionCreator w : map.keySet()) {
			if (choice < probabilities.get(w)) {
				return w.getSection(startX,startY,d);
			}
		}
		throw new RuntimeException("SOMETHING IS TERRIBLY WRONG");
	}
	public static abstract class SectionCreator{
		public final int probability;
		public SectionCreator(int probability){
			this.probability=probability;
		}
		public abstract DungeonSection getSection(int startX,int startY,Direction d);
	}
}
