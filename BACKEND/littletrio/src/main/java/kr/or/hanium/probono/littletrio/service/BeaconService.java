package kr.or.hanium.probono.littletrio.service;

import kr.or.hanium.probono.littletrio.domain.Beacon;
import kr.or.hanium.probono.littletrio.domain.BeaconRepository;
import kr.or.hanium.probono.littletrio.domain.Result;
import kr.or.hanium.probono.littletrio.exception.NonExistentResourceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class BeaconService {
    @Resource
    private BeaconRepository beaconRepository;

    public Beacon add(String deviceNumber) {
        return beaconRepository.save(new Beacon(deviceNumber));
    }

    @Transactional
    public Result register(String deviceNumber) {
        return Result.of().addParameter("beacon", beaconRepository.findByDeviceNumber(deviceNumber)
                .orElseThrow(NonExistentResourceException::new)
                .setState(true))
                .ok();
    }
}
