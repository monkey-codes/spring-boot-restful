package monkey.codes.prometheus

import io.prometheus.client.exporter.common.TextFormat
import org.springframework.boot.actuate.endpoint.mvc.AbstractEndpointMvcAdapter
import org.springframework.boot.actuate.endpoint.mvc.HypermediaDisabled
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by jzietsman on 1/28/16.
 */
class PrometheusMvcEndpoint extends AbstractEndpointMvcAdapter<PrometheusEndpoint>{

    PrometheusMvcEndpoint(PrometheusEndpoint delegate) {
        super(delegate)
    }

    @RequestMapping(method = RequestMethod.GET, produces = TextFormat.CONTENT_TYPE_004)
    @ResponseBody
    @HypermediaDisabled
    protected Object invoke() {
        if (!getDelegate().isEnabled()) {
            return new ResponseEntity<Map<String, String>>(
                    Collections.singletonMap("message", "This endpoint is disabled"),
                    HttpStatus.NOT_FOUND)
        }
        super.invoke()
    }
}
