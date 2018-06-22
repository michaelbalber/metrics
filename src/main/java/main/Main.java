package main;
  import com.codahale.metrics.*;
  import java.util.concurrent.TimeUnit;

  public class Main {
    static final MetricRegistry metrics = new MetricRegistry();
   
    public static void main(String args[]) throws InterruptedException {
      startReport();
      Meter requests = metrics.meter("requests");
      while(true) {
    	  requests.mark();
    	  Thread.sleep(100);
      }
      //wait5Seconds();
    }

  static void startReport() {
      ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
          .convertRatesTo(TimeUnit.SECONDS)
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