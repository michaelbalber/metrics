package main;

import java.util.LinkedList;

public class TimerLinkedList {
	LinkedList<Long> list = new LinkedList<Long>();
	public void pushLast(Long val) {
		list.addLast(val);
		long beforeMinuteTime = System.nanoTime()-60_000_000_000L;
		while(true) {
			Long firstTime = list.getFirst();
			if(beforeMinuteTime > firstTime) {
				list.removeFirst();
			}else {
				break;
			}
		}
	}
	public int size() {
		return list.size();
	}
}
