package kr.or.hanium.probono.littletrio.domain;

import javax.persistence.*;

@Entity
public class Beacon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 12)
    private int deviceNumber;
    @Column(nullable = false)
    private int state;

    public Beacon() {
    }

    public Beacon(Long id, int deviceNumber) {
        this.id = id;
        this.deviceNumber = deviceNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(int deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
