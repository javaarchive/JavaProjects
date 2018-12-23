import java.io.*;
import java.security.MessageDigest;
import java.security.*;
public class compilestandard extends javatools {
	public static String sha256(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
         
        return sb.toString();
    }
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader f;
		if(args.length==1) {
			if(args[0].equals("eclipse")) {
				f=mreader("src/javatools.java");
			}else {
				f=mreader("javatools.java");
			}
			
		}else {
				f=mreader("javatools.java");
			}
		
		print("build");
		
		String st;
		String full="";
		
		while ((st = f.readLine()) != null) {
		    full=full+st+"\n";
		  }
		String text=full.replace("if(!(lock)) {return null;}", "");
		text=text.replace("public abstract class usacotools","public abstract class jt");
	 	print(text);
	 	PrintWriter pw=mwriter("jt.java");
	 	if(args[0].equals("eclipse")) {
			pw=mwriter("src/jt.java");
		}else {
			
		}
	 	pw.println(text);
	 	pw.close();
	 	print("jt.java is the one you should copy into your code but usacotools is the one you should extend");
	 try{
		 print("jt.java SHA256: "+sha256(text));
	 	print("javatools.java SHA256: "+sha256(full));
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
	 	
	}
}
