package kr.or.hanium.probono.littletrio.b4showing.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Subway {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Size(min = 4, max = 4)
    private String trainNumber;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_subway_to_subwayLine"))
    private SubwayLine subwayLine;

    @OneToMany(mappedBy = "subway", cascade = CascadeType.ALL)
    private List<Room> rooms = new ArrayList<>();

    public Subway(String trainNumber, List<Room> rooms) {
        this.trainNumber = trainNumber;
        this.rooms = rooms;
    }
}
