package main;

import java.util.LinkedList;
import java.util.concurrent.PriorityBlockingQueue;

public class MovingAverageQueue<T extends TimeAndValue> {
	private PriorityBlockingQueue<T> queue= new PriorityBlockingQueue<T>();
	private double total=0;
	private double timeWindowMillis=0;

	public MovingAverageQueue(double timeWindowMillis) {
		this.timeWindowMillis = timeWindowMillis;
	}

	public synchronized void pushLastAndRemoveOld(T element) {
		queue.offer(element);
		total+=element.getVal();
		double beforeMinuteTime = System.nanoTime()/1_000_000.0-timeWindowMillis;
		T head = queue.peek();
		while(head!=null && head.getTime() < beforeMinuteTime) {
			System.out.println(head.getTime());
			queue.poll();
			total-=head.getVal();
			head = queue.peek();
		}
	}

	public int size() {
		return queue.size();
	}

	public double getAverage() {
		return total/size();
	}

}
