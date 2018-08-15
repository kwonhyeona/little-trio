package kr.or.hanium.probono.littletrio.b4showing.service;

import kr.or.hanium.probono.littletrio.b4showing.domain.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
}
