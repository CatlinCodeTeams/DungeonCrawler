package dungeon.backend.generation.section.config;

import dungeon.connectionInterfaces.CellType;

public class ItemConfig {
	public final Item[] items;
	public ItemConfig(Item[] i){
		items=i;
	}
	public enum ItemType{
		SET_NUM, CHANCE;
	}
	public static class Item{

		public final ItemType type;
		public final CellType target;
		public final double value;

		public Item(CellType targetType,ItemType type, double value){
			this.type=type;
			this.target=targetType;
			this.value=value;
		}
	}
}
