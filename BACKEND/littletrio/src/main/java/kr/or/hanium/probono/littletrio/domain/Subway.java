package kr.or.hanium.probono.littletrio.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Subway {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long trainNumber;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_subway_to_subwayLine"))
    private Subway subwayLine;
    @OneToMany(mappedBy = "subway", cascade = CascadeType.ALL)
    @OrderBy("id ASC")
    private List<Room> rooms;

    public Subway() {
    }

    public Subway(Long trainNumber, List<Room> rooms) {
        this.trainNumber = trainNumber;
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Subway{" +
                "id=" + id +
                ", trainNumber=" + trainNumber +
                ", subwayLine=" + subwayLine +
                ", rooms=" + rooms +
                '}';
    }
}
