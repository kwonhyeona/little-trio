package kr.or.hanium.probono.littletrio.web;

import kr.or.hanium.probono.littletrio.domain.Nfc;
import kr.or.hanium.probono.littletrio.service.NfcService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public ResponseEntity<Nfc> create(@Valid @RequestBody String deviceNumber) {
        return ResponseEntity.status(HttpStatus.CREATED).body(nfcService.add(deviceNumber));
    }

    @PostMapping("/register")
    public ResponseEntity<Nfc> register(@Valid @RequestBody String deviceNumber) {
        log.debug("nfc register : {}", deviceNumber);
        return ResponseEntity.status(HttpStatus.OK).body(nfcService.register(deviceNumber));
    }
}
