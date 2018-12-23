
public class objectWithId extends object{
	int id;
	static int NextId = 0;
	public objectWithId() {
		this.id = NextId;
		NextId++;
	}
	public boolean strictequals(objectWithId x) {
		return (x.id == this.id);
	}
	public static void main(String[] args) {
		objectWithId o1 = new objectWithId();
		objectWithId o2 = new objectWithId();
		objectWithId o3 = new objectWithId();
		System.out.println(o3.NextId);
		System.out.println(o3.id);
		System.out.println(o1.id);
	}

}
