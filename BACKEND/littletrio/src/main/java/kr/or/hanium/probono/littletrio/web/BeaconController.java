package kr.or.hanium.probono.littletrio.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BeaconController {
    @GetMapping("/")
    public String show() {
        return "/index.html";
    }
}
