package Tools;
/*
 * FlexMap implementation for String, Bool
 * 
 */
import java.util.*;

public class FlexMapStringBool implements FlexMap{
	Map<String,Boolean> core;
	public FlexMapStringBool(){
		this.core = new HashMap<String,Boolean>();
	}
	public void put(String a,boolean b) {
		this.core.put(a, b);
	}
	public boolean get(Object a) {
		if(this.core.containsKey(a)) {
			return this.core.get(a);
		}else {
			return false;
		}
	}
	public boolean contains(String s) {
		return this.core.containsKey(s);
	}
	public boolean containsval(boolean s) {
		return this.core.containsValue(s);
	}
	public void clear() {
		this.core.clear();
	}
	public static void main(String[] args) {
		System.out.println("Running self test");
		FlexMapStringBool fm = new FlexMapStringBool();
		fm.put("k", true);
		System.out.println("true = "+fm.get("k"));
		System.out.println("false = "+fm.get("e"));
	}

}
