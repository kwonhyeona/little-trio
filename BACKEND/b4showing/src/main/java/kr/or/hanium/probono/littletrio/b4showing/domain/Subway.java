package kr.or.hanium.probono.littletrio.b4showing.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
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
    @Length(min = 4, max = 4)
    private Long trainNumber;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_subway_to_subwayLine"))
    private SubwayLine subwayLine;

    @OneToMany(mappedBy = "subway", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Room> rooms = new ArrayList<>();

    public Subway(Long trainNumber, List<Room> rooms) {
        this.trainNumber = trainNumber;
        this.rooms = rooms;
    }
}