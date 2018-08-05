package support;

import kr.or.hanium.probono.littletrio.domain.Nfc;
import kr.or.hanium.probono.littletrio.domain.NfcRepository;
import kr.or.hanium.probono.littletrio.domain.Subway;
import kr.or.hanium.probono.littletrio.domain.SubwayRepository;
import kr.or.hanium.probono.littletrio.exception.NonExistentResourceException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class AcceptanceTest {
    private static final String DEFAULT_LOGIN_USER = "gusdk@gusdk.com";

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private SubwayRepository subwayRepository;

    public TestRestTemplate template() {
        return template;
    }

    protected Subway findByTrainNumber(Long trainNumber) {
        return subwayRepository.findByTrainNumber(trainNumber)
                .orElseThrow(() -> new NonExistentResourceException("일치하는 열차가 없습니다."));
    }

    protected String createResource(String path, Object bodyPayload) {
        ResponseEntity<String> response = template().postForEntity(path, bodyPayload, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        return response.getHeaders().getLocation().getPath();
    }
}