package kr.or.hanium.probono.littletrio.b4showing.web;

import kr.or.hanium.probono.littletrio.b4showing.domain.Subway;
import kr.or.hanium.probono.littletrio.b4showing.service.SubwayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/api/subways")
public class SubwayController {

    @Resource
    private SubwayService subwayService;

    @PostMapping("")
    public ResponseEntity<Subway> create(@RequestBody Subway subway) {
        log.debug("SubwayController create : {}", subway);
        return ResponseEntity.status(HttpStatus.CREATED).body(subwayService.create(subway));
    }

    @GetMapping("/{trainNumber}")
    public ResponseEntity<Subway> getRoomsByTrainNumber(@PathVariable String trainNumber) {
        log.debug("SubwayController : {}", trainNumber);
        return ResponseEntity.status(HttpStatus.OK).body(subwayService.getRooms(trainNumber));
    }
}
