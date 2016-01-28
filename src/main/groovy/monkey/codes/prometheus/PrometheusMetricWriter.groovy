package monkey.codes.prometheus

import io.prometheus.client.CollectorRegistry
import io.prometheus.client.Counter
import io.prometheus.client.Gauge
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.actuate.metrics.Metric
import org.springframework.boot.actuate.metrics.writer.Delta
import org.springframework.boot.actuate.metrics.writer.MetricWriter

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

/**
 * Created by jzietsman on 1/27/16.
 */
class PrometheusMetricWriter implements MetricWriter {

    CollectorRegistry registry
    private final ConcurrentMap<String, Gauge> counters = new ConcurrentHashMap<>()
    private final ConcurrentHashMap<String, Gauge> gauges = new ConcurrentHashMap<>()

    @Autowired
    public PrometheusMetricWriter(CollectorRegistry registry){
        this.registry = registry
    }

    @Override
    void increment(Delta<?> delta) {
        counter(delta.name).inc(delta.value.doubleValue())
        p 'increment', "$delta.name $delta.value"
    }

    @Override
    void reset(String metricName) {
        counter(metricName).clear()
        p 'reset', metricName
    }

    @Override
    void set(Metric<?> value) {
        gauge(value.name).set(value.value.doubleValue())
        p 'set', "$value.name=$value.value"
    }

    private Counter counter(name){
        def key = sanitizeName(name)
        counters.computeIfAbsent key, {k  ->
                println "Creating new counter $k"
                Counter.build().name(k).help(k).register(registry)
        }
    }

    private Gauge gauge(name){
        def key = sanitizeName(name)
        gauges.computeIfAbsent key, {k ->
            Gauge.build().name(k).help(k).register(registry)
        }
    }
    
    private String sanitizeName(String name) {
        return name.replaceAll("[^a-zA-Z0-9_]", "_");
    }

    def p(prefix, value){
        println '-' * 100
        println "$prefix: $value"
        println '-' * 100
    }
}
