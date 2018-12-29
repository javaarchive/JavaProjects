/*
 * Pixilated games engine by javaarchive
 * Copyright 2018
 * Version 1.0.0
 */
package Engine;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

public class grid extends settings implements KeyListener,MouseListener{
	public coord getPos(int x,int y) {
		coord out = new coord(0,0);
		out.x = Math.floorDiv(x, BLOCK_SIZE);
		out.y = Math.floorDiv(y, BLOCK_SIZE);
		return out;
		
	}
	public void mouseClick(coord c) {
		
	}
public void mousePress(coord c) {
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.mouseClick(getPos(arg0.getX(),arg0.getY()));
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		this.mousePress(getPos(arg0.getX(),arg0.getY()));
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void down() {
		
	}
	public void up() {
		
	}
	public void right() {
		
	}
	public void left() {
		
	}
    public void delete() {
		
	}
	public void escape() {
		this.frame.dispose();
	}
	public void windows() {
		
	}
	
	public void keyTyped(KeyEvent e) {
		//System.err.println("Triggered");
        if(e.getKeyCode()==KeyEvent.VK_DOWN) {
        	down();
        }else if (e.getKeyCode()==KeyEvent.VK_UP) {
        	up();
        }else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
        	left();
        }else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
        	right();
        }else if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
        	escape();
        }else if(e.getKeyCode()==KeyEvent.VK_DELETE) {
        	delete();
        }else if(e.getKeyCode()==KeyEvent.VK_WINDOWS) {
        	windows();
        }
    }

    /** Handle the key-pressed event from the text field. */
    public void keyPressed(KeyEvent e) {
        this.keyTyped(e);
    }

    /** Handle the key-released event from the text field. */
    public void keyReleased(KeyEvent e) {
        
    }
	protected JFrame frame;
	public void customDraw(Graphics g) {
		
	}
	public void createLabelText(String text) {
		JLabel textLabel = new JLabel(text);
		this.frame.add(textLabel);
		textLabel.setVisible(true);
	}
	public void createLabelText(String text,Font customFont) {
		JLabel textLabel = new JLabel(text);
		this.frame.add(textLabel);
		textLabel.setFont(customFont);
		textLabel.setVisible(true);
	}
	public void paint(Graphics g) {
		//System.out.println(Arrays.deepToString(this.Grid));
		//System.out.println("Drawing.....");
		int[][] Grid=this.Grid;
		Image os;
		if(doubleBuffer) {
			os = this.createImage(this.getWidth(), this.getHeight());
		}
		for(int i=0;i<this.Grid.length;i++) {
			for(int j=0;j<this.Grid[i].length;j++) {
				//System.out.println("Drawing "+i+" "+j+" "+Grid[i][j]);
				if(doubleBuffer) {
					
					Graphics g2 = os.getGraphics();
					//System.out.println(colorIds[Grid[i][j]] != Color.RED);
					g2.setColor(colorIds[this.Grid[i][j]]);
					g2.fillRect(j*BLOCK_SIZE, i*BLOCK_SIZE,BLOCK_SIZE,BLOCK_SIZE);
					g2.setColor(BORDER_COLOR);
					g2.drawRect(j*BLOCK_SIZE, i*BLOCK_SIZE, BLOCK_SIZE,BLOCK_SIZE);
					//this.paint(g2);
					
					
				}else {
				g.setColor(colorIds[this.Grid[i][j]]);
				g.fillRect(j*BLOCK_SIZE, i*BLOCK_SIZE,BLOCK_SIZE,BLOCK_SIZE);
				g.setColor(BORDER_COLOR);
				g.drawRect(j*BLOCK_SIZE, i*BLOCK_SIZE, BLOCK_SIZE,BLOCK_SIZE);
				}
			}
		}
		if(doubleBuffer) {
			g.drawImage(os, 0, 0, this);
		}
		os = null;
		customDraw(g);
	}
	public void paintComponent(Graphics g) {
		this.paint(g);
	}
	public int[][] Grid;
	public grid(int x,int y) {
		System.out.println("Creating grid");
		this.Grid = new int[x][y];
		this.frame = new JFrame("Unnamed Grid Window");
		this.prepareGui();
	}
	
	public JPanel jp;
	public grid(String name,int x,int y) {
		System.out.println("Creating grid with name "+name);
		this.Grid = new int[x][y];
		this.frame = new JFrame(name);
		//this.jp = new JPanel();
		//this.jp.add(this);
		//this.frame.add(jp);
		this.frame.add(this);
		this.prepareGui();
		
	}
	public void drawGrid() {
		this.repaint();
	}
	public void closeApp() {
		this.frame.dispose();
	}
	public void prepareGui() {
		this.drawGrid();
		this.frame.setSize(WIDTH, HEIGHT);
		
		if(FULL_SCREEN) {
		this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.frame.setUndecorated(true);
		}
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.addKeyListener(this);
		this.frame.addKeyListener(this);
		this.frame.addMouseListener(this);
	}
	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e) {
			e.printStackTrace();
		}
		grid g=new grid("Grid",5,5);
		g.Grid[2][2]=3;
	}
public void addToUi(Component comp) {
	this.frame.add(comp);
}

}

