package monkey.codes.prometheus

import io.prometheus.client.CollectorRegistry
import io.prometheus.client.exporter.common.TextFormat
import org.springframework.boot.actuate.endpoint.AbstractEndpoint

/**
 * Created by jzietsman on 1/28/16.
 */
class PrometheusEndpoint extends AbstractEndpoint<String> {

    CollectorRegistry registry

    PrometheusEndpoint(CollectorRegistry registry) {
        super('prometheus',false, true)
        this.registry = registry
    }

    @Override
    String invoke() {
        Writer writer = new StringWriter()
        TextFormat.write004(writer, registry.metricFamilySamples());
        writer.toString()
    }
}
