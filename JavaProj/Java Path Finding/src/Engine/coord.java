package Engine;

public class coord {
	public int x;
	public int y;
	public coord(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public coord() {
		this.x=0;
		this.y=0;
	}
	@Override
	public String toString() {
		return "("+this.x+","+this.y+")";
	}
	
}