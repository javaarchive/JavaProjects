import java.awt.*;
import java.awt.image.*;

import javax.swing.*;

public class Render extends JPanel{
	@Override
	public void paint(Graphics g) {
		Graphics offgc;
	    
	    Dimension d = this.getSize();
	    Image offscreen = this.createImage(d.width, d.height);
	    // create the offscreen buffer and associated Graphics
	    offscreen = createImage(d.width, d.height);
	    offgc = offscreen.getGraphics();
	    // clear the exposed area
	    offgc.setColor(getBackground());
	    offgc.fillRect(0, 0, d.width, d.height);
	    offgc.setColor(this.getForeground());
	    // do normal redraw
	    this.draw.draw(offgc);
	    // transfer offscreen to window
	    g.drawImage(offscreen, 0, 0, this);
		this.draw.draw(g);
		offscreen = null;
	}
	Paintable draw;
	public Render(Paintable p) {
		this.draw = p;
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
