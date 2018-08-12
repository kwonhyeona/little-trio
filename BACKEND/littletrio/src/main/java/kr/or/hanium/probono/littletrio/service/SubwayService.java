package kr.or.hanium.probono.littletrio.service;

import kr.or.hanium.probono.littletrio.domain.Room;
import kr.or.hanium.probono.littletrio.domain.Subway;
import kr.or.hanium.probono.littletrio.domain.SubwayRepository;
import kr.or.hanium.probono.littletrio.exception.NonExistentResourceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Iterable<Room> getRooms(Long trainNumber) {
        log.debug("SubwayService getRooms : {}", findByTrainNumber(trainNumber));
        return findByTrainNumber(trainNumber).getRooms();
    }

    private Subway findByTrainNumber(Long trainNumber) {
        return subwayRepository.findByTrainNumber(trainNumber)
                .orElseThrow(() -> new NonExistentResourceException("해당 번호와 일치하는 열차가 없습니다."));
    }
}
