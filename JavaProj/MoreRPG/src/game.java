import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.*;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class game extends settings implements Paintable{
	JFrame frame;
	Render panel;
	public ArrayList<BufferedImage> assets = new ArrayList<BufferedImage>();
	public void load(String filename) {
		
		try {
            assets.add(ImageIO.read(new File(filename)));
            loaded ++;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

	}
	public int loaded = 0;
	public boolean loading;
	public void loadassets() {
		loading = true;
		for(int i = 0; i < 485; i ++) {
		StringBuilder temp = new StringBuilder(Integer.toString(i));
		System.out.println(i);
		String lf = Integer.toString(i);
		if(temp.toString().length() - 4 < 0  ) {
			for(int j = 0 ; j < 4 - lf.length(); j ++) {
				temp.insert(0, '0');
				
			}
		}
		lf = temp.toString();
		System.out.println(lf);
		load("assets/Tiles/tile_"+lf+".png");
		this.panel.repaint();
		}
		loading = false;
	}
	public void buildui() {
		this.frame = new JFrame(NAME);
		this.frame.setVisible(true);
		this.frame.setSize(400, 400);
		panel = new Render(this);
		this.frame.add(panel);
	}
	public game() {
		this.buildui();
		this.loadassets();
	}

	public static void main(String[] args) {
		game Game = new game();

	}
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, this.panel.getWidth(), this.panel.getHeight());
		if(loading) {
			g.setColor(Color.RED);
			g.drawString("Loaded "+loaded+" assets", 20, 20);
		}
		
	}

}
