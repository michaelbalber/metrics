package main;
  import com.codahale.metrics.*;
  import java.util.concurrent.TimeUnit;

  public class Main {
    static final MetricRegistry metrics = new MetricRegistry();
   
    public static void main(String args[]) throws InterruptedException {
      startReport();
      Meter requests = metrics.meter("requests");
      Counter counter = metrics.counter("counter");
      Timer timer = metrics.timer("timer");
      while(true) {
    	  Timer.Context context1 = timer.time();
    	  requests.mark();
    	  counter.inc();
    	  Thread.sleep((long)(10*Math.random()));
    	  context1.stop();
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