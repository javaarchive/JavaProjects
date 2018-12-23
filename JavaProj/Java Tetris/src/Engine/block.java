package Engine;

import java.util.ArrayList;

public class block {
	public ArrayList<coord> points;
	public static int[][] rotate90cw(int[][] map) {
    	/*
    	 * Rotate 2D array 
    	 * 90 degree clockwise 
    	 */
    	int N=map.length;
    	int[][] n=new int[map[0].length][N];
    	for(int i=0;i<N;i++) {
    		for(int j=0;j<N;j++) {
    			n[j][N-1-i]=map[i][j];
    		}
    	}
    	return n;
    }
	public void rotate() {
		ArrayList<coord> newp = new ArrayList<coord>();
		for(coord c:this.points) {
			newp.add(new coord(c.y,c.x));
		}
		this.points = newp;
	}
	public int color;
	public block(ArrayList<coord> coords, int color) {
		this.points=coords;
		this.color=color;
	}
	
	
}