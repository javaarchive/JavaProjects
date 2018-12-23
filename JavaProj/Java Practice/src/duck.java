import java.util.ArrayList;

public class duck extends animal {
	public duck(String type) {
		super(type);
		this.init();
		// TODO Auto-generated constructor stub
	}

	public duck() {
		// TODO Auto-generated constructor stub
		super();
		this.init();
	}

	public duck(int age) {
		super(age);
		this.init();
		// TODO Auto-generated constructor stub
	}

	public duck(String type, int age) {
		super(type, age);
		this.init();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		duck a = new duck();
		a.quack();
		a.quack();
		a.quack();
		a.quack();
		ArrayList<duck> ducks = new ArrayList<duck>();
		ducks.add(a);
		ducks.add(new duck(10));
		ducks.add(new duck(12));
		ducks.add(new duck(11));
		ducks.add(new duck(8));
		ducks.add(new duck(5));
		ducks.sort(null);
		System.out.println(ducks);
	}
	public void init() {
		this.initRand(noises.length - 1);
		//this.init_qRandom();
		
	}
	public String[] noises = {"Quack!","Quack Quack","Quackity quack"};
	public void quack() {
		//this.print(this.getRandom());
		this.print(noises[this.getRandom()]);
	}
	
	

}
