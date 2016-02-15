package monkey.codes

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

import javax.servlet.http.HttpServletResponse

/**
 * Created by jzietsman on 1/27/16.
 */
@Controller
class TestController {

    @RequestMapping("/hello")
    public void test(@RequestParam('name') String name,  HttpServletResponse resp){
        resp.setStatus(HttpServletResponse.SC_OK);
//        resp.setContentType(TextFormat.CONTENT_TYPE_004);

        Writer writer = resp.getWriter();
        writer.write("this is something else $name")
//        TextFormat.write004(writer, registry.metricFamilySamples());
        writer.flush();
        writer.close();
    }
}
