package kr.or.hanium.probono.littletrio.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SubwayLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 10)
    private String lineName;
    @Column
    private int roomCount;
    @OneToMany(mappedBy = "subwayLine", cascade = CascadeType.ALL)
    @OrderBy("id ASC")
    private List<Subway> subways = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public List<Subway> getSubways() {
        return subways;
    }

    public void setSubways(List<Subway> subways) {
        this.subways = subways;
    }
}
