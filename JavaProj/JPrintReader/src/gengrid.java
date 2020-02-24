import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;

public class gengrid {
	public static final String OUTPUT_FILE = "grid.png";
	public static final int HEIGHT = 50;
	public static final int WIDTH = 35;
	public static final int OFFSETX = 20;
	public static final int OFFSETY = 20;
	public static final int ROWS = 12;
	public static final int COLS = 8;
	static boolean doPrint = true;
	public static void main(String[] args) throws IOException {
		BufferedImage bi = new BufferedImage(OFFSETX + WIDTH * COLS + 1,OFFSETY + ROWS * HEIGHT + 1,BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.getGraphics();
		g.fillRect(0, 0, bi.getWidth(), bi.getHeight());
		for(int i = 0; i < ROWS; i ++) {
			for(int j = 0; j < COLS; j ++) {
				//g.setColor(Color.white);
				//g.fillRect(j * WIDTH, i * HEIGHT, WIDTH, HEIGHT);
				g.setColor(Color.black);
				g.drawRect(OFFSETX + j * WIDTH,OFFSETY + i * HEIGHT, WIDTH, HEIGHT);
				
			}
		}
		PrinterJob printJob = PrinterJob.getPrinterJob();
		printJob.setPrintable(new Printable() {
		        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		        	Paper p = new Paper();
		        	//p.setImageableArea(0, 0, bi.getWidth(), bi.getHeight());
		        	p.setSize(bi.getWidth(), bi.getHeight());
		        	pageFormat.setPaper(p);
		                if (pageIndex != 0) {
		                    return NO_SUCH_PAGE;
		                }
		                graphics.drawImage(bi, 0, 0, bi.getWidth(), bi.getHeight(), null);
		                return PAGE_EXISTS;
		        }
		});     
		try {
			if(doPrint && printJob.printDialog()) {
				HashPrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
				attr.add(new MediaPrintableArea(0f, 0f, bi.getWidth()/72f, bi.getHeight()/72f, MediaPrintableArea.INCH));
			    printJob.print();
			}
		} catch (PrinterException e1) {             
		    e1.printStackTrace();
		}
		File outputfile = new File(OUTPUT_FILE);
		ImageIO.write(bi, "png", outputfile);
	}

}
