package kr.or.hanium.probono.littletrio.b4showing.service;

import kr.or.hanium.probono.littletrio.b4showing.domain.Subway;
import kr.or.hanium.probono.littletrio.b4showing.domain.SubwayRepository;
import kr.or.hanium.probono.littletrio.b4showing.exception.NonExistentResourceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SubwayService {
    @Autowired
    private SubwayRepository subwayRepository;

    public Subway get(Long trainNumber) {
        return findByTrainNumber(trainNumber);
    }

    public Subway create(Subway subway) {
        return subwayRepository.save(subway);
    }

    public List<String> getRooms(Long trainNumber) {
        log.debug("SubwayService getRooms : {}", findByTrainNumber(trainNumber));
        return findByTrainNumber(trainNumber).getRooms()
                .stream().map(room -> room.getSeatInfo())
                .collect(Collectors.toList());
    }

    private Subway findByTrainNumber(Long trainNumber) {
        return subwayRepository.findByTrainNumber(trainNumber)
                .orElseThrow(() -> new NonExistentResourceException("해당 번호와 일치하는 열차가 없습니다."));
    }
}
