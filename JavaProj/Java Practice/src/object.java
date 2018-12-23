import java.util.PrimitiveIterator.OfInt;
import java.util.Random;
import java.util.stream.IntStream;

import org.w3c.dom.*;
/*
 * Child of all objects
 */
public class object {
	public object() {
		
	}
	@Override
	public object clone() {
		return new object();
	}
	public static void main(String[] args) {
		System.out.println("This is not meant to be run");
		System.exit(1);
	}
	public void print(String out) {
		System.out.println(out);
	}
	public void print(int out) {
		System.out.println(out);
	}
	public void display(String out) {
		System.out.println(out);
	}
	public void display(boolean out) {
		System.out.println(out);
	}
	public Random rand;
	public int randn;
	public void initRand() {
		this.randn = 100;
		this.rand = new Random();
	}
	public void initRand(int high) {
		this.randn = high;
		this.rand = new Random();
		
	}
	public int getRandom() {
		return this.rand.nextInt(this.randn);
	}
	public OfInt randStream;
	public void init_qRandom() {
		this.randStream = this.rand.ints().iterator();
	}
	public int qRandom() {
		return -1;
		//return this.randStream.nextInt(this.randn);
	}
}
