package kr.or.hanium.probono.littletrio.service;

import kr.or.hanium.probono.littletrio.domain.Subway;
import kr.or.hanium.probono.littletrio.domain.SubwayRepository;
import kr.or.hanium.probono.littletrio.exception.NonExistentResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubwayService {
    @Autowired
    private SubwayRepository subwayRepository;

    public Subway get(Long trainNumber) {
        return subwayRepository.findByTrainNumber(trainNumber)
                .orElseThrow(() -> new NonExistentResourceException("해당 번호와 일치하는 열차가 없습니다."));
    }

    public Subway create(Subway subway) {
        return subwayRepository.save(subway);
    }
}
