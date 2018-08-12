package kr.or.hanium.probono.littletrio.service;

import kr.or.hanium.probono.littletrio.domain.Room;
import kr.or.hanium.probono.littletrio.domain.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
}
