package kr.or.hanium.probono.littletrio.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_room_to_subway"))
    private Subway subway;
    @Column
    private int number;
    @Column(length = 4)
    private String seatInfo;

    public Room(String seatInfo) {
        this.seatInfo = seatInfo;
    }
}
