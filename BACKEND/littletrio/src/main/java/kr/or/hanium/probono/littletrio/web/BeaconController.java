package kr.or.hanium.probono.littletrio.web;

import kr.or.hanium.probono.littletrio.domain.Beacon;
import kr.or.hanium.probono.littletrio.domain.BeaconRepository;
import kr.or.hanium.probono.littletrio.domain.Result;
import kr.or.hanium.probono.littletrio.exception.NonExistentResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/beacon")
public class BeaconController {
    @Autowired
    private BeaconRepository beaconRepository;

    @GetMapping("")
    public String show() {
        return "/index.html";
    }

    @PostMapping("/new")
    public Beacon create(String deviceNumber) {
        return beaconRepository.save(new Beacon(deviceNumber));
    }

    @PostMapping("")
    public Result register(String deviceNumber) {
        return Result.of().addParameter("beacon", beaconRepository.findByDeviceNumber(deviceNumber)
                .orElseThrow(NonExistentResourceException::new)
                .setState(true))
                .ok();
    }
}
