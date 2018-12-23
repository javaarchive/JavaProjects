
public class animal extends objectWithId implements Comparable<animal>{
	public int age;
	public String type;
	public static int default_age = 0;
	public animal(String type) {
		this.age = default_age;
		this.type = type;
	}
	public animal() {
		this.age = default_age;
		this.type = "unknown";
	}
	public animal(int age) {
		this.age = age;
		this.type = "unknown";
	}
	public animal(String type,int age) {
		this.type = type;
		this.age = age;
	}
	public int compareTo(animal a) {
		return this.age - a.age;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("animal(age = ");
		sb.append(this.age);
		sb.append(", type = ");
		sb.append(this.type);
		sb.append(")");
		return sb.toString();
	}
	public boolean equals(animal a) {
		System.out.println("Matching age...");
		if(a.age == this.age) {
			print("Age matches");
			if(this.type.equals(a.type)) {
				print("Type matches");
				if(this.id == a.id) {
					print("Same animal");
					return true;
				}
			}
		}
		return false;
	}
	public static void line() {
		System.out.println("===================================================================");
	}
	public static void main(String[] args) {
		animal a1 = new animal();
		animal a2 = new animal(10);
		animal a3 = new animal("polar bear",10);
		animal a4 = new animal("polar bear",11);
		animal a5 = new animal("polar bear",10);
		line();
		a1.display(a1.equals(a2));
		line();
		a2.display(a3.equals(a5));
		line();
		a5.display(a3.equals(a4));
		line();
		a3.display(a3.equals(a3));
		line();
	}

}
