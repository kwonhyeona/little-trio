package kr.or.hanium.probono.littletrio.b4showing.web;

import kr.or.hanium.probono.littletrio.b4showing.domain.Nfc;
import kr.or.hanium.probono.littletrio.b4showing.domain.NfcRepository;
import kr.or.hanium.probono.littletrio.b4showing.error.ErrorResponse;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiNfcAcceptanceTest extends AcceptanceTest {
    private static final Logger log = LoggerFactory.getLogger(ApiNfcAcceptanceTest.class);

    @Autowired
    private NfcRepository nfcRepository;
    private Nfc defaultNFC;
    private Nfc defaultNFC2;
    private Nfc wrongNFC;

    @Before
    public void setUp() throws Exception {
        defaultNFC = new Nfc("123451234512");
        defaultNFC2 = new Nfc("000000000000");
        wrongNFC = new Nfc("12345");
    }

    @Test
    public void nfc_생성() {
        ResponseEntity<Nfc> response = template().postForEntity("/api/nfcs", defaultNFC, Nfc.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        log.debug("nfc_등록 : {}", response.getBody());
        log.debug("nfc_등록 : {}", nfcRepository.findAll());
    }

    @Test
    public void nfc_생성_기기번호_오류() {
        ResponseEntity<ErrorResponse> response = template().postForEntity("/api/nfcs", wrongNFC, ErrorResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        log.debug("nfc_등록_기기번호_오류 : {}", response.getBody().getMessage());
    }

    @Test
    public void nfc_등록() {
        template().postForEntity("/api/nfcs", defaultNFC, Nfc.class);

        ResponseEntity<Nfc> response = template().postForEntity("/api/nfcs/register", defaultNFC, Nfc.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        log.debug("nfc_등록 : {}", response.getBody());
        log.debug("nfc_등록 : {}", nfcRepository.findAll());
    }

    @Test
    public void nfc_등록_기기번호_오류() {
        ResponseEntity<ErrorResponse> response = template().postForEntity("/api/nfcs/register", wrongNFC, ErrorResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        log.debug("nfc_등록_기기번호_오류 : {}", response.getBody().getMessage());
    }

    @Test
    public void nfc_등록_미생성_NFC() {
        ResponseEntity<ErrorResponse> response = template().postForEntity("/api/nfcs/register", defaultNFC, ErrorResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        log.debug("nfc_등록_기기번호_오류 : {}", response.getBody().getMessage());
    }

    @Test
    public void nfc_등록_이미_등록된_NFC() {
        template().postForEntity("/api/nfcs", defaultNFC.getDeviceNumber(), Nfc.class);
        template().postForEntity("/api/nfcs/register", defaultNFC.getDeviceNumber(), ErrorResponse.class);

        ResponseEntity<ErrorResponse> response = template().postForEntity("/api/nfcs/register", defaultNFC, ErrorResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        log.debug("nfc_등록_기기번호_오류 : {}", response.getBody().getMessage());
    }
}