package com.github.engineermike.metrics;

import com.codahale.metrics.Counter;
import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;

import static com.codahale.metrics.MetricRegistry.name;

public class MetricsSampleApp {

    public static final MetricRegistry metrics = new MetricRegistry();

    public static void main(String[] args) throws InterruptedException {
        MetricsSampleApp app = new MetricsSampleApp();
        JmxReporter jmxReporter = app.createReporter();
        app.runCounter();

        Thread.sleep(60 * 1000);
        jmxReporter.stop();
    }

    private void runCounter() {
        Counter pendingJobs = metrics.counter(name(MetricsSampleApp.class, "pending-jobs"));

        pendingJobs.inc();
        pendingJobs.inc(2);
        pendingJobs.dec();
    }

    private JmxReporter createReporter() {
        JmxReporter jmxReporter = JmxReporter.forRegistry(metrics).build();
        jmxReporter.start();
        return jmxReporter;
    }



}
