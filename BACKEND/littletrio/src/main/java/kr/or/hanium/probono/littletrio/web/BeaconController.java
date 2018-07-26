package kr.or.hanium.probono.littletrio.web;

import kr.or.hanium.probono.littletrio.domain.Beacon;
import kr.or.hanium.probono.littletrio.domain.Result;
import kr.or.hanium.probono.littletrio.service.BeaconService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/beacon")
public class BeaconController {
    @Resource
    private BeaconService beaconService;

    @GetMapping("")
    public String show() {
        return "/index.html";
    }

    @PostMapping("/new")
    public Beacon create(String deviceNumber) {
        return beaconService.add(deviceNumber);
    }

    @PostMapping("")
    public Result register(String deviceNumber) {
        return beaconService.register(deviceNumber);
    }
}
