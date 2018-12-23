import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class d0wnloader {
	private static JFrame temp;
	public static void download(String url, String out) throws IOException {
		System.out.println("DL "+url+" "+out);
		URL website;
		try {
			website = new URL(url);
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			FileOutputStream fos = new FileOutputStream(new File(out));
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE );
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Internet error, press enter to proceed");
			JOptionPane.showMessageDialog(temp , "Failed to download file due to a network error. Here's your debug data "+e.getMessage());
			(new PrintWriter(new FileWriter("Warning_Download_Failed.warning"))).close();
		}
	
	}
	public static void main(String[] args) throws Exception{
		String repoBase = "https://raw.githubusercontent.com/"; // Need slash
		String repoEnd = "javaarchive/java-tools/master/"; // username/repo-name/branch/
		String u = repoBase + repoEnd;
		temp = new JFrame("Download...");
		download(u + "javatools.java","javatools.java");
		download(u + "jt.java","jt.java");
		download(u + "compilestandard.java","compilestandard.java");
		JOptionPane.showMessageDialog(temp , "Downloader is done!");
		temp.dispose();
	}

}
