package Tools;

public class ReportErrors {
	public static final int MAX_ERRORS = 10;
	public static int errcount = 0;
	public static void check() {
		if(errcount > MAX_ERRORS) {
			System.exit(2);
		}
	}
	public static void error() {
		errcount ++;
		check();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
