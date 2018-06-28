package main;

import java.util.LinkedList;

public class TimerLinkedList<T extends TimeAndValue> {
	LinkedList<T> list = new LinkedList<T>();
	double total=0;
	public void pushLast(T element) {
		list.addLast(element);
		total+=element.getVal();
		long beforeMinuteTime = System.nanoTime()-60_000_000_000L;
		while(true) {
			T first = list.getFirst();
			if(beforeMinuteTime > first.getTime()) {
				list.removeFirst();
				total-=first.getVal();
			}else {
				break;
			}
		}
	}
	
	public int size() {
		return list.size();
	}
	
	public double getAverage() {
		return total/list.size();
	}
	
}
