package Tools;

public class constantprop {
	private FlexMapStringBool core;
	private FlexMapStringBool map;
	int SIZE;
	public constantprop(int size) {
		this.SIZE = size;
	}
	public void set(String a,boolean b){
		if(!(core.contains(a))) {
		core.put(a, true);
		map.put(a, b);
		}else {
			ReportErrors.error();
		}
	}
	public boolean contains(String a) {
		return map.contains(a);
	}
	public boolean get(String a) {
		return map.get(a);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
