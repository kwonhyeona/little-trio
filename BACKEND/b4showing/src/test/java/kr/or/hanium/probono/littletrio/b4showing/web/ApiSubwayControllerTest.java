package kr.or.hanium.probono.littletrio.b4showing.web;

import kr.or.hanium.probono.littletrio.b4showing.domain.Room;
import kr.or.hanium.probono.littletrio.b4showing.domain.Subway;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.AcceptanceTest;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiSubwayControllerTest extends AcceptanceTest {
    private static final Logger log = LoggerFactory.getLogger(ApiSubwayControllerTest.class);

    private Subway subway;

    @Before
    public void setUp() throws Exception {
        subway = new Subway("2000", Arrays.asList(
                new Room("0000"),
                new Room("1000"),
                new Room("1010"),
                new Room("0100"),
                new Room("0001")
        ));
    }

    @Test
    public void create() {
        log.debug("Test create : {}", subway);
        ResponseEntity<Subway> response = template().postForEntity("/api/subways", subway, Subway.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        log.debug("getSubwayByTrainNumber : {}", response.getBody());
    }

    @Test
    public void getRoomsByTrainNumber_저장되어있는_열차번호() {
        ResponseEntity<Subway> response = template().getForEntity(String.format("/api/subways/%s", subway.getTrainNumber()), Subway.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        log.debug("getSubwayByTrainNumber : {}", response.getBody());
    }

    @Test
    public void getRoomsByTrainNumber_저장되어있지않은_열차번호() {
        ResponseEntity<Subway> response = template().getForEntity("/api/subways/8000", Subway.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        log.debug("getSubwayByTrainNumber : {}", response.getBody());
    }
}