package kr.or.hanium.probono.littletrio.b4showing.web;

import kr.or.hanium.probono.littletrio.b4showing.domain.Nfc;
import kr.or.hanium.probono.littletrio.b4showing.service.NfcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/nfcs")
public class NfcController {

    @Resource
    private NfcService nfcService;

    @GetMapping("")
    public String show() {
        return "/index.html";
    }

    @PostMapping("")
    public ResponseEntity<Nfc> create(@Valid @RequestBody Nfc nfc) {
        log.info("deviceNumber: {}", nfc.getDeviceNumber());
        return ResponseEntity.status(HttpStatus.CREATED).body(nfcService.add(nfc));
    }

    @PostMapping("/register")
    public ResponseEntity<Nfc> register(@Valid @RequestBody Nfc nfc) {
        log.debug("nfc register : {}", nfc.getDeviceNumber());
        return ResponseEntity.status(HttpStatus.OK).body(nfcService.register(nfc));
    }
}
