package main;

import java.util.LinkedList;
import java.util.concurrent.PriorityBlockingQueue;

public class MovingAverageQueue<T extends TimeAndValue> {
	PriorityBlockingQueue<T> queue= new PriorityBlockingQueue<T>();
	double total=0;
	public void pushLast(T element) {
		queue.offer(element);
		total+=element.getVal();
		long beforeMinuteTime = System.nanoTime()-6_000_000_000L;
		while(true) {
			T first = queue.peek();
			if(beforeMinuteTime > first.getTime()) {
				System.out.println(first.getTime());
				queue.poll();
				total-=first.getVal();
			}else {
				break;
			}
		}
	}
	
	public int size() {
		return queue.size();
	}
	
	public double getAverage() {
		return total/size();
	}
	
}
