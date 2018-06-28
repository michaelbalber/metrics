package main;
import com.codahale.metrics.*;

import java.util.LinkedList;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Main {
	static final MetricRegistry metrics = new MetricRegistry();

	public static void main(String args[]) throws InterruptedException {
		MovingAverageQueue<TimeAndValue> list = new MovingAverageQueue<>();
	//	startReport();
		Meter requests = metrics.meter("requests");
		Counter counter = metrics.counter("counter");
		//Timer timer = metrics.timer("timer");
		while(true) {
			//Timer.Context context1 = timer.time();
			requests.mark();
			counter.inc();
			list.pushLast(new TimeAndValue(System.nanoTime(),(int)(100*Math.random())));
			//System.out.println("size:"+list.size());
			//System.out.println(list.getAverage());
			Thread.sleep((long)(100*Math.random()));
			//  context1.stop();
		}
		//wait5Seconds();
	}

	static void startReport() {
		ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
				.convertRatesTo(TimeUnit.MINUTES)
				.convertDurationsTo(TimeUnit.MILLISECONDS)
				.build();
		reporter.start(5, TimeUnit.SECONDS);
	}

	static void wait5Seconds() {
		try {
			Thread.sleep(5*1000);
		}
		catch(InterruptedException e) {}
	}
}