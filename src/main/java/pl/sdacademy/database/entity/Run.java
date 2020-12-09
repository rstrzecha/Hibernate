package pl.sdacademy.database.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "RUN")
public class Run {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer membersLimit;

    @Column(length = 50)
    private String city;

    @OneToMany(mappedBy = "run", fetch = FetchType.EAGER)   //nazwa pola łączącego w RunMember
                            // , fetch = FetchType.EAGER - dołożone do Eager
    private Set<RunMember> members;

    public Run() {}

    public Set<RunMember> getMembers() {
        return members;
    }

    public void setMembers(Set<RunMember> members) {
        this.members = members;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getMembersLimit() {
        return membersLimit;
    }

    public String getCity() {
        return city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMembersLimit(Integer membersLimit) {
        this.membersLimit = membersLimit;
    }

    public void setCity(String city) {
        this.city = city;
    }
}