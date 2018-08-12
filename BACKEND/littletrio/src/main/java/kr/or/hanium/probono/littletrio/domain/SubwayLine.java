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
//    @OneToMany(mappedBy = "subwayLine", cascade = CascadeType.ALL)
//    @OrderBy("id ASC")
//    private List<Subway> subways = new ArrayList<>();
}
