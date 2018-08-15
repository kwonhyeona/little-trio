package kr.or.hanium.probono.littletrio.b4showing.service;

import kr.or.hanium.probono.littletrio.b4showing.domain.Nfc;
import kr.or.hanium.probono.littletrio.b4showing.domain.NfcRepository;
import kr.or.hanium.probono.littletrio.b4showing.exception.NonExistentResourceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class NfcService {
    @Resource
    private NfcRepository nfcRepository;

    public Nfc add(Nfc nfc) {
        return nfcRepository.save(nfc);
    }

    @Transactional
    public Nfc register(Nfc nfc) {
        return nfcRepository.findByDeviceNumber(nfc.getDeviceNumber())
                .orElseThrow(() -> new NonExistentResourceException("해당 기기번호로 등록된 NFC가 존재하지 않습니다."))
                .setStateTrue(1);
    }
}
