package Tools;

public class Rotate {
	double deg;
	double rad;
	public Rotate() {
		this.deg = 0;
		this.rad = 0;
	}
	public Rotate rotate(Degree degree) {
		this.deg = degree.value;
		this.rad = degree.toRadian().value;
		return this;
	}
	public Rotate rotate(Radian degree) {
		this.deg = degree.value;
		this.rad = degree.toDegree().value;
		return this;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
