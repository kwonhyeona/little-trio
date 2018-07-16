package kr.or.hanium.probono.littletrio.domain;

import javax.persistence.*;

@Entity
public class Beacon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 12)
    private String deviceNumber;
    @Column(nullable = false)
    private boolean state;

    public Beacon() {
    }

    public Beacon(String deviceNumber) {
        checkLengthOfDeviceNumber(deviceNumber);

        this.deviceNumber = deviceNumber;
    }

    public Beacon(Long id, String deviceNumber) {
        checkLengthOfDeviceNumber(deviceNumber);

        this.id = id;
        this.deviceNumber = deviceNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public boolean getState() {
        return state;
    }

    public Beacon setState(boolean state) {
        this.state = state;
        return this;
    }

    private void checkLengthOfDeviceNumber(String deviceNumber) {
        if (deviceNumber.length() != 12) throw new IllegalArgumentException("인자의 길이가 12보다 짧습니다.");
    }
}
