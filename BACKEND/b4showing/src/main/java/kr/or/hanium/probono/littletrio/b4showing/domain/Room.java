package kr.or.hanium.probono.littletrio.b4showing.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Getter
@Entity
@ToString(exclude = "subway")
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_room_to_subway"))
    private Subway subway;
    @Column
    private int number;
    @Column
    @Length(min = 4, max = 4)
    @ColumnDefault("0000")
    private String seatInfo;

    public Room(String seatInfo) {
        this.seatInfo = seatInfo;
    }

    public Room(Subway subway, int number, String seatInfo) {
        this.subway = subway;
        this.number = number;
        this.seatInfo = seatInfo;
    }
}
