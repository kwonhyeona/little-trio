package kr.or.hanium.probono.littletrio.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SubwayRepository extends CrudRepository<Subway, Long> {
    Optional<Subway> findByTrainNumber(Long trainNumber);
}
