package kr.or.hanium.probono.littletrio.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BeaconRepository extends CrudRepository<Beacon, Long> {
    Optional<Beacon> findByDeviceNumber(String deviceNumber);
}
