package kr.or.hanium.probono.littletrio.web;

import kr.or.hanium.probono.littletrio.domain.Subway;
import kr.or.hanium.probono.littletrio.service.SubwayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/subways")
public class SubwayController {
    private static final Logger log = LoggerFactory.getLogger(SubwayController.class);
    @Resource
    private SubwayService subwayService;

    @PostMapping("")
    public ResponseEntity<Subway> create(@RequestBody Subway subway) {
        log.debug("SubwayController create : {}", subway);
        return ResponseEntity.status(HttpStatus.CREATED).body(subwayService.create(subway));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subway> getSubwayByTrainNumber(@PathVariable Long trainNumber) {
        log.debug("SubwayController : {}", trainNumber);
        return ResponseEntity.status(HttpStatus.OK).body(subwayService.get(trainNumber));
    }
}
