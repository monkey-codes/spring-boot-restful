package codes.monkey.prometheus

import io.prometheus.client.CollectorRegistry
import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter
import org.springframework.boot.actuate.autoconfigure.ManagementContextConfiguration
import org.springframework.boot.actuate.condition.ConditionalOnEnabledEndpoint
import org.springframework.boot.actuate.metrics.writer.MetricWriter
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.context.annotation.Bean

/**
 * Created by jzietsman on 1/28/16.
 */
@ManagementContextConfiguration
class PrometheusEndpointContextConfiguration {

    @Bean
    PrometheusEndpoint prometheusEndpoint(CollectorRegistry registry) {
        new PrometheusEndpoint(registry)
    }

    @Bean
    @ConditionalOnBean(PrometheusEndpoint.class)
    @ConditionalOnEnabledEndpoint("prometheus")
    PrometheusMvcEndpoint prometheusMvcEndpoint(PrometheusEndpoint prometheusEndpoint) {
        new PrometheusMvcEndpoint(prometheusEndpoint)
    }

    @Bean
    CollectorRegistry collectorRegistry() {
        CollectorRegistry.defaultRegistry
    }

    @Bean
    @ExportMetricWriter
    MetricWriter prometheusMetricWriter(CollectorRegistry registry) {
        new PrometheusMetricWriter(registry)
    }

}
