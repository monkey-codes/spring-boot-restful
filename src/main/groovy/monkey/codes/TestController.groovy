package monkey.codes

import io.prometheus.client.CollectorRegistry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

import io.prometheus.client.exporter.common.TextFormat
import javax.servlet.http.HttpServletResponse

/**
 * Created by jzietsman on 1/27/16.
 */
@Controller
class TestController {

    CollectorRegistry registry

    @Autowired
    public TestController(CollectorRegistry registry){
        this.registry = registry
    }

    @RequestMapping("/prometheus")
    public void test(HttpServletResponse resp){
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType(TextFormat.CONTENT_TYPE_004);

        Writer writer = resp.getWriter();
        TextFormat.write004(writer, registry.metricFamilySamples());
        writer.flush();
        writer.close();
    }
}
