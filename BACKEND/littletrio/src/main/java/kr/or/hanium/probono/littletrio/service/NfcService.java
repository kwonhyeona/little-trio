package kr.or.hanium.probono.littletrio.service;

import kr.or.hanium.probono.littletrio.domain.Nfc;
import kr.or.hanium.probono.littletrio.domain.NfcRepository;
import kr.or.hanium.probono.littletrio.exception.NonExistentResourceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class NfcService {
    @Resource
    private NfcRepository nfcRepository;

    public Nfc add(String deviceNumber) {
        return nfcRepository.save(new Nfc(deviceNumber));
    }

    @Transactional
    public Nfc register(String deviceNumber) {
        return nfcRepository.findByDeviceNumber(deviceNumber)
                .orElseThrow(() -> new NonExistentResourceException("해당 기기번호로 등록된 NFC가 존재하지 않습니다."))
                .setStateTrue(true);
    }
}
