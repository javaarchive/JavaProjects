
public class repl {
	// Lang Translate
	public static final String NO_FILE_SPECIFIED = "No file specified. Termianting...";
	public String[] parseCommandSegement(String cmd) {
		return cmd.split("(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
	}
	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println(NO_FILE_SPECIFIED);
		}
		
	}

}
