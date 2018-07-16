package kr.or.hanium.probono.littletrio.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Subway {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_subway_to_subwayLine"))
    private SubwayLine subwayLine;
    @OneToMany(mappedBy = "subway", cascade = CascadeType.ALL)
    @OrderBy("id ASC")
    private List<Room> rooms = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SubwayLine getSubwayLine() {
        return subwayLine;
    }

    public void setSubwayLine(SubwayLine subwayLine) {
        this.subwayLine = subwayLine;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
