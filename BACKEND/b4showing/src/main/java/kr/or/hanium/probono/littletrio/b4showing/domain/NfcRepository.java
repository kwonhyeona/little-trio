package kr.or.hanium.probono.littletrio.b4showing.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NfcRepository extends CrudRepository<Nfc, Long> {
    Optional<Nfc> findByDeviceNumber(String deviceNumber);
}
