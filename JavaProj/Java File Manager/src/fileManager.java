import java.awt.Desktop;
import java.io.*;
import java.util.*;
public class fileManager {
	private static Scanner sc;
	public static String getExt(String fileName) {
		String extension = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
		    extension = fileName.substring(i+1);
		}
		return extension;
	}
	public static void lines() {
		for(int i=0;i<50;i++) {System.out.println("\n");}
	}
	public static exitCode openFile(String fullpath) {
		File f = new File(fullpath);
		String ext = getExt(f.getName());
		try {
		if(f.isDirectory()) {
			return new exitCode(f.getCanonicalPath(), true);
		}
		else if(ext.equals("html") || ext.equals("htm")) {
			Desktop.getDesktop().browse(f.toURI());
		}else if(ext.equals("pdf")) {
			//Runtime.getRuntime().exec("start "+f.getCanonicalPath());
			Desktop.getDesktop().open(f);
		}else if(ext.equals("docx") || ext.equals("doc") || ext.equals("ppt") || ext.equals("pptx") ) {
			Desktop.getDesktop().edit(f);
		}else if(ext.equals("mp4") || ext.equals("m4a") || ext.equals("m4a") 
				|| ext.equals("mp3") || ext.equals("ogg") || ext.equals("wav") ||
				ext.equals("avi") || ext.equals("png") || ext.equals("jpg") || ext.equals("bmp")){
			Desktop.getDesktop().open(f);
		}else if(ext.equals("txt")) {
			String line;
			BufferedReader br = new BufferedReader(new FileReader(f));
			System.out.println("======================= Text file viewer 1.0.1 ==========================");
			while ((line = br.readLine()) != null) {
				   System.out.println(line);
			}
			br.close();
			System.out.print("Press enter to continue > ");
			sc.nextLine();
			System.out.println();
		}
		}catch(Exception e) {
			System.out.println("Failed to open file");
			System.out.println("Error message: "+e.getMessage());
		}
		return new exitCode(false);
		
		
	}
	public static ArrayList<File> getFiles(File folder) {
		ArrayList<File> files = new ArrayList<File>();
	    for (File fileEntry : folder.listFiles()) {
	        files.add(fileEntry);
	        
	    }
	    return files;
	}
	public static void printfiles(ArrayList<File> files) {
		for(int i=0;i<files.size();i++) {
			System.out.println(i+": "+files.get(i).getName());
		}
	}
	public static void main(String[] args) {
		String curDir =	"C:\\Users\\fath\\OneDrive\\usaco_resources_handouts";
		ArrayList<File> files;
		sc = new Scanner(System.in);
		int id;
		final String REMOVE_KEYWORD = "rm ";
		boolean end = false;
		exitCode ec;
		String word = "";
		while(!(end)) {
			lines();
			System.out.println("\n Viewing directory "+curDir);
			files = getFiles(new File(curDir));
			printfiles(files);
			id = -3;
			while(id<-2 || id>=files.size()) {
				System.out.print("File number (-1 to exit, -2 for parent directory):");
				//id = sc.nextInt();
				try {
				word = sc.nextLine();
				id = Integer.parseInt(word);
				}catch(Exception e) {
					if(word.equals("..") || word.equals("...")) {
						id = -2;
						break;
					}else if(word.equals("exit") || word.equals("quit") || word.equals("x") || word.equals("close")){
						id = -1;
						break;
					}else if(word.equals("refresh") || word.equals(".")) {
						files = getFiles(new File(curDir));
						lines();
						printfiles(files);
						break;
				    }else if(word.startsWith(REMOVE_KEYWORD)) {
				    	System.out.println("Confirm deletion of "+files.get(Integer.parseInt(word.substring(REMOVE_KEYWORD.length()))).getName()+" Y/N");
				    	String in = sc.nextLine();
				    	System.out.println();
				    	if(in.toLowerCase().equals("y") || in.toLowerCase().equals("yes")) {
				    	files.get(Integer.parseInt(word.substring(REMOVE_KEYWORD.length()))).delete();
				    	files = getFiles(new File(curDir));
				    	System.out.println("Deleted file");
				    	}else {
				    		System.out.println("Canceled deletetion");
				    	}
				    	//break;
				    }
					else {
					
					System.out.println("That's not a number");
					}
				}
			}
			if(id == -1) {
				end = true;
				break;
			}
			try{
				if(id == -2) {
					curDir = (new File(curDir)).getParent();
				}else {
				ec = openFile(files.get(id).getCanonicalPath());
				if(ec.done) {
					curDir = ec.newDir;
				}
				}
			}catch(Exception e) {
				System.out.println("Error: "+e.getMessage());
			}
			
			
		}
		sc.close();
	}

}
class exitCode{
	public boolean done;
	public String newDir;
	public exitCode(boolean done) {
		this.done = done;
		this.newDir = "C:\\";
	}
	public exitCode(String newDir, boolean done) {
		this.done = done;
		this.newDir = newDir;
	}
}