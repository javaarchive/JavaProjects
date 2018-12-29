package Tetris;
import Engine.grid.*;
import Engine.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.UIManager;
import Engine.grid;
public class JTetris extends grid implements ActionListener{
	public ArrayList<block> blocks;
	public final static int ROWS = 108;
	public final static int COLS = 192;
	public final static int REFRESH_RATE = 200;
	public int score=0;
	public final String GAME_OVER = "Game Over! Press escape to exit!";
	public int[][] clearMask;
	public JLabel scoreLabel;
	public JTetris(String name) {
		super(name, ROWS, COLS);
		clearMask = new int[ROWS][COLS];
		System.out.println("Setup clear mask complete");
		scoreLabel = new JLabel("Score: "+Integer.toString(score));
		//scoreLabel.setHorizontalAlignment(JLabel.RIGHT);
		scoreLabel.setForeground(Color.CYAN);
		//scoreLabel.setBackground(Color.PINK);
		scoreLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		scoreLabel.setOpaque(true);
		scoreLabel.setVisible(true);
		this.add(scoreLabel);
		//this.frame.remove(this);
		//this.frame.add(this);
		//this.createLabelText("test")
		 
	}
	
	@Override
	public void delete() {
		this.game_done = true;
		this.restartAction = true;
		this.closeApp();
	}
	public boolean checkForBlocks() {
		boolean foundRow=false;
		boolean rowfull=true;
		for(int i=0;i<this.Grid.length;i++) {
			rowfull=true;
			for(int j:this.Grid[i]) {
				
				//System.out.println("");
				if(j==0) {
					rowfull=false;
				}
			}
			if (rowfull) {
				foundRow=true;
				System.out.println("Row "+i+" is full");
				score++;
				scoreLabel.setText("Score: "+Integer.toString(score));
				this.Grid[i]=new int[COLS];
				for(int k=i-1;k>-1;k--) {
					System.out.println("Shifting "+i+" to "+(i+1));
					this.Grid[i+1]=this.Grid[i].clone();
				}
			}
		}
		return foundRow;
	}
	public void clear() {
		for(int i=0;i<this.Grid.length;i++) {
			for(int j=0;j<this.Grid[i].length;j++) {
				if(this.clearMask[i][j]==0) {
					this.Grid[i][j]=0;
				}
			}
		}
	}
	@Override
	public void up() {
		this.clear();
		this.curBlock.rotate();
		
		this.paint(this.getGraphics());
	}
	@Override
	public void right() {
		System.out.println("Right key pressed");
		if(xx+hx[this.blockId]+1<COLS) {
		xx++;
		}
	}
	@Override
	public void left() {
		System.out.println("Left key pressed");
		if(xx-1 > -1) {
		xx--;
		}
	}
	@Override
	public void down() {
		System.out.println("Down key pressed");
		this.repaint();
		yy++;
	}
	public boolean game_done = false;
	@Override
	public void customDraw(Graphics g) {
		if(this.game_done) {
			g.setColor(Color.GREEN);
		
		g.setFont(new Font("Arial" ,Font.PLAIN, 50));
		g.drawString(GAME_OVER,0 , (int) this.getSize().getHeight()/2);
		}
	}
	public Random rnum = new Random();
	public int blockId = -1;
	public block getBlock() {
		//(int) Math.random() * (this.blocks.size()+1)
		blockId = rnum.nextInt(this.blocks.size());
		return this.blocks.get(
				 blockId
				);
	}
	
	public void createBlock(block b,int x,int y) {
		//System.out.println(b);
		try {
		for(coord Coord:b.points) {
			//System.out.println((Coord.x+x)+" "+(Coord.y+y)+"+++++++"+b.color);
			if(this.Grid[Coord.x+x][Coord.y+y] != 0) {
				throw new Exception("Cannot place block. Stopping block fall");
			}
			this.Grid[Coord.x+x][Coord.y+y] = b.color;
			
			
		}
		} catch(Exception e) {
			try {
			//e.printStackTrace();
			System.out.println("Hit floor");
			for(coord Coord:b.points) {
				this.Grid[Coord.x+x-1][Coord.y+y] = b.color;
				this.clearMask[Coord.x+x-1][Coord.y+y] = 1;
			}
			boolean found=false;
			try{
				found = this.checkForBlocks();
			
			}catch(Exception exx) {
				found=false;
			}
			for(int i:this.Grid[0]) {
				if(i!=0 && !(found)) {
					System.out.println("GAME OVER!");
					throw new Exception("Finsihed game detected");
				}
			}
			
			curBlock = getBlock();
			xx=0;
			yy=0;
			}catch(Exception ex) {
				//ex.printStackTrace();
				this.game_done = true;
				this.timer.stop();
				System.out.println("game ended");
			}
			
		}
		//System.out.println(Arrays.deepToString(this.clearMask));

	}
	public int xx=0;
	public int yy=0;
	public block curBlock;
	private Timer timer;
	public void startGame() {
		//Game loop
		buildblocks();
		curBlock = getBlock();
		//System.out.println("Starting GAME!");
		timer = new Timer(REFRESH_RATE, this);
        timer.start();
		
	}
	public boolean restartAction = false;
	public void actionPerformed(ActionEvent e) {
		
		
		
			try {
    	//System.out.println("Draw 2");
    	this.createBlock(curBlock,yy,xx);
    	this.paint(this.getGraphics());
    	//System.out.println("Repainting");
		
		
		/*
		try {
			Thread.sleep(REFRESH_RATE);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		*/
		yy++;
		clear();
		
		}catch(Exception et) {
			this.timer.stop();
		}
		
    }
	public int[] hx;
	public int[] hy;
	public void buildblocks() {
		blocks = new ArrayList<block>();
		int x1,y1;
		int x2,y2;
		try {
		BufferedReader f = new BufferedReader(new FileReader("block.data"));
		int N = Integer.parseInt(f.readLine());
		int M;
		hx = new int[N];
		hy = new int[N];
		ArrayList<coord> block1 ;
		StringTokenizer st;
		for(int i=0;i<N;i++) {
			M = Integer.parseInt(f.readLine());
			block1 = new ArrayList<coord>();
			x2=0; y2=0;
			for(int j=0;j<M;j++) {
				st= new StringTokenizer(f.readLine());
				x1=Integer.parseInt(st.nextToken());
				y1=Integer.parseInt(st.nextToken());
				if(x1>x2) {
					x2=x1;
				}
				if(y1>y2) {
					y2=y1;
				}
				block1.add(new coord(x1,y1));
			}
			hx[i] = x2;
			hy[i] = y2;
			blocks.add(new block(block1,Integer.parseInt(f.readLine())));
			
		}
		f.close();
		} catch(Exception e) {
			// TODO Catch exception code
		}
	}
	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e) {
			e.printStackTrace();
		}
		JTetris jt=new JTetris("Java Tetris");
		jt.startGame();
		
		while(true) {
			try {
			Thread.sleep(2000);
			}catch(Exception e) {
				
			}
			if(jt.game_done == true) {
				if(jt.restartAction) {
					System.out.println("Starting new game");
					jt = new JTetris("Java Tetris");
					jt.startGame();
					jt.delete();
				}else {
					break;
				}
			}
		}
	}

}
