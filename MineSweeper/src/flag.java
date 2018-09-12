
public class flag extends Tile {

	public flag(Object o) {
		super(o);
		
	}
	@Override
	public boolean equals(Object o){
		if(o instanceof flag)
			return true;
		return false;
	}
	public Object get(){
		return hidden;
	}
}
