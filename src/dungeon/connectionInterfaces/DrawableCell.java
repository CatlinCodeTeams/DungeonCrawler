package dungeon.connectionInterfaces;

public interface DrawableCell {
	public enum CellType{
		Wall,Floor
	}
	
	public int getX();
	public int getY();
	public CellType getType();
	//d = getCellAt(3,5);
	//if (d.getType()==CellType.Wall)
	
	
}
