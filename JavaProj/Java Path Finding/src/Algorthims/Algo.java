package Algorthims;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Engine.*;
public class Algo {
	public final static int ROWS = 108;
	public final static int COLS = 192;
	public static double pythogorean(int a,int b) {
		return Math.sqrt(a*a + b*b);
	}
	public static int sx,sy,ex,ey;
	public static double getdist(int x1,int y1,int x2,int y2) {
		return pythogorean(Math.abs(x2-x1),Math.abs(y2-y1));
	}
	public static boolean inrange(int x,int y) {
		if(0 <= x && x < ROWS && 0 <= y && y< COLS) {
			return true;
		}
		return false;
	}
	public static int[] bx = {0,1, 0,-1,1,-1, 1,-1};
	public static int[] by = {1,0,-1, 0,1,-1,-1, 1};
	public static void startAlgorthim() {
		sortcoord sortc= getSort();
		int x = sx,y = sy;
		coord newcoord;
		ArrayList<coord> coords =  new ArrayList<coord>();
		while(x!=ex || y!=ey) {
			coords.clear();
			g.Grid[x][y]= 2;
			for(int i=0;i<bx.length;i++) {
			if(inrange(x + bx[i],y + by[i])) {
				if(g.Grid[x + bx[i]][y + by[i]] == 0) {
					coords.add(new coord(x + bx[i],y + by[i]));
					g.Grid[x + bx[i]][y + by[i]] = 3;
					g.drawGrid();
				}
			}
			}
			System.out.println(x+" "+y);
			coords.sort(sortc);
			Collections.reverse(coords);
			newcoord = coords.get(0);
			x = newcoord.x;
			y = newcoord.y;
			g.drawGrid();
			sleep(100);
		}
	
	}
	public static sortcoord getSort() {
		sortcoord sc= new sortcoord(sx,sy,ex,ey) {
			@Override
			public int compare(coord a,coord b) {
				return Double.compare(getdist(this.ssx,this.ssy,a.x,a.y),getdist(this.eex,this.eey,a.x,a.y) );
			}
		};
		return sc;
	}
	public static void sleep(long mills) {
		try {
			Thread.sleep(mills);
		}catch(Exception e) {
			
		}
	}
	public static grid g;
	public static int mode = 0;
	public static void main(String[] args) {
		g = new grid("Visual",ROWS,COLS) {
			public void mousePress(coord c) {
				System.out.println(c);
				if(mode % 2 == 0) {
					sx = c.x;
					sy = c.y;
				}else {
					ex = c.x;
					ey = c.y;
					startAlgorthim();
				}
				mode++;
			}
			public void mouseClick(coord c) {
				System.out.println("click "+c);
			}
			
		};
		
		g.drawGrid();
		
		

	}

}
class sortcoord implements Comparator<coord> 
{ 
    // Used for sorting in ascending order of 
    // roll name 
	public int ssx,ssy,eex,eey;
	public sortcoord(int sx,int sy,int ex,int ey) {
		this.ssx = sx;
		this.ssy = sy;
		this.eex = ex;
		this.eey = ey;
	}
    public int compare(coord a, coord b) 
    { 
        return 0; // To be overrided
    } 
} 
