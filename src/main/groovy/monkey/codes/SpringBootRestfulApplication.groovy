package monkey.codes

import io.prometheus.client.CollectorRegistry
import monkey.codes.prometheus.PrometheusMetricWriter
import org.springframework.boot.SpringApplication
import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter
import org.springframework.boot.actuate.metrics.writer.MetricWriter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SpringBootRestfulApplication {

	@Bean
	CollectorRegistry collectorRegistry(){
		return new CollectorRegistry()
	}

	@Bean
	@ExportMetricWriter
	MetricWriter prometheusMetricWriter(CollectorRegistry registry){
		new PrometheusMetricWriter(registry)
	}

	static void main(String[] args) {
		SpringApplication.run SpringBootRestfulApplication, args
	}
}
