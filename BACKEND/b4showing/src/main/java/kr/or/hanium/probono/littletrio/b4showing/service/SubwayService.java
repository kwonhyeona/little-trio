package kr.or.hanium.probono.littletrio.b4showing.service;

import kr.or.hanium.probono.littletrio.b4showing.domain.Room;
import kr.or.hanium.probono.littletrio.b4showing.domain.RoomRepository;
import kr.or.hanium.probono.littletrio.b4showing.domain.Subway;
import kr.or.hanium.probono.littletrio.b4showing.domain.SubwayRepository;
import kr.or.hanium.probono.littletrio.b4showing.exception.NonExistentResourceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SubwayService {
    @Autowired
    private SubwayRepository subwayRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Transactional
    public Subway create(Subway subway) {
        return subwayRepository.save(subway);
    }

    public Subway getRooms(String trainNumber) {
        return findByTrainNumber(trainNumber);
    }

    private Subway findByTrainNumber(String trainNumber) {
        return subwayRepository.findByTrainNumber(trainNumber)
                .orElseGet(() -> createSubwayAndRooms(trainNumber));
    }

    @Transactional
    Subway createSubwayAndRooms(String trainNumber) {
        List<Room> rooms = new ArrayList<>();
        Subway subway = subwayRepository.save(new Subway(trainNumber));
        for (int i = 0; i < 10; i++) {
            rooms.add(roomRepository.save(new Room(subway, i + 1, "0000")));
        }

        subway.setRooms(rooms);
        return subway;
    }
}
