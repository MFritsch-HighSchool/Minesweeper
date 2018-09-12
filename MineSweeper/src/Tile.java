
public class Tile {
	Object hidden;
	@Override
	public boolean equals(Object b){
		if(b instanceof Tile)
			return true;
		return false;
	}
	public Tile(Object o){
		hidden = o;
	}
	public Object get(){
		return hidden;
	}
}
