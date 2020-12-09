package pl.sdacademy.database.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "RUN_MEMBER")
public class RunMember {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50)
    private String name;
    @Column(name = "start_number")
    private Integer startNumber;

    @ManyToOne
    @JoinColumn(name = "run_id")           //nazwa kolumny łączącej tabele
    private Run run;                       //przypisanie do biegu, Hibernate to sobie złączy

    @ManyToMany(mappedBy = "members")       //dowiązanie do pola members w klasie NfcTag
    private Set<NfcTag> tags = new HashSet<>();

    public RunMember() {}

    public Run getRun() {
        return run;
    }

    public void setRun(Run run) {
        this.run = run;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getStartNumber() {
        return startNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartNumber(Integer startNumber) {
        this.startNumber = startNumber;
    }
}
