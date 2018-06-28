package main;

public class TimeAndValue implements Comparable<TimeAndValue>{
	double time=0;
	double val;
	public double getTime() {
		return time;
	}
	public double getVal() {
		return val;
	}
	public TimeAndValue(double time, double val) {
		super();
		this.time = time;
		this.val = val;
	}
	
	@Override
	public int compareTo(TimeAndValue t) {
		if(t==null) {
			return 1;
		}
		
		return (int) ( time - t.getTime());
	}
	
	
	
	
}
