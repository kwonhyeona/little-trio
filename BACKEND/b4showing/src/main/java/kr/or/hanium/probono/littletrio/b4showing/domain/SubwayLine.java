package kr.or.hanium.probono.littletrio.b4showing.domain;

import javax.persistence.*;

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
