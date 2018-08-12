package kr.or.hanium.probono.littletrio.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Data
@Entity
@ToString
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
    private String seatInfo = "0000";

    public Room(String seatInfo) {
        this.seatInfo = seatInfo;
    }
}
