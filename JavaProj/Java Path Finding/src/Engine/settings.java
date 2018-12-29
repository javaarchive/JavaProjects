package Engine;

import java.awt.*;
import javax.swing.*;
/*
 * Pixilated games engine settings by javaarchive
 * Configure as you wish
 */
// DO NOT REMOVE EXTENDS CANVAS
public class settings extends JPanel{
/*
 * Grid settings documentation
 * colorIds - Color lookup table. You should change this for your own needs. Note that the first color is the default color.
 * 
 */
public final Color[] colorIds = {Color.RED,Color.BLUE,Color.YELLOW,Color.CYAN,new Color(255, 255, 102)};
public final Color BORDER_COLOR = Color.BLUE;
//public final int BLOCK_SIZE = 10;
public final int BLOCK_SIZE = 100;
public final boolean FULL_SCREEN = false;
public final int WIDTH = 480;
public final int HEIGHT = 640;
public final boolean doubleBuffer = true; // Enables double buffering to reduce flickering. 
}
