package kr.or.hanium.probono.littletrio.b4showing.domain;

import kr.or.hanium.probono.littletrio.b4showing.exception.AlreadyExistentResourceException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Entity
@ToString
@NoArgsConstructor
public class Nfc {
    private static final Logger log = LoggerFactory.getLogger(Nfc.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @Size(min = 12, max = 12)
    private String deviceNumber;
    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean state;

    public Nfc(String deviceNumber) {
        log.debug("Nfc constructor : {}", deviceNumber);
        this.deviceNumber = deviceNumber;
    }

    public Nfc(Long id, String deviceNumber) {
        this.id = id;
        this.deviceNumber = deviceNumber;
    }

    public Nfc setStateTrue(boolean state) {
        if(this.state) throw new AlreadyExistentResourceException("이미 등록된 NFC 기기입니다.");
        this.state = state;
        return this;
    }
}
