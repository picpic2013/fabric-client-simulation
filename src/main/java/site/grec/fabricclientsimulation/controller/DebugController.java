package site.grec.fabricclientsimulation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import site.grec.fabricclientsimulation.dao.copyright.PiracyInfo;
import site.grec.fabricclientsimulation.service.CopyrightService;

@Controller
@ResponseBody
public class DebugController {
    @Autowired
    CopyrightService copyrightService;

    @ResponseBody
    @GetMapping("/debug/hello")
    public String helloRequest() {
        return "Hello World";
    }

//    http://localhost:8080//debug/piracy-info?url=123&content=456
    @GetMapping("/debug/piracy-info")
    public PiracyInfo debugPiracyInfoRequest(
            @RequestParam("url") String url,
            @RequestParam("content") String content
    ) {
        return copyrightService.getDebugPiracyInfo(url, content);
    }
}
