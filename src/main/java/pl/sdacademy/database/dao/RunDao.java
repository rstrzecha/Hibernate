package pl.sdacademy.database.dao;

import pl.sdacademy.database.entity.Run;

import java.util.List;

public interface RunDao {       //CRUD
    void save(Run run);         //C U    // nie potrzeba update, bo tutaj to zrobimy
    Run findById(Long id);      //R
    List<Run> findAll();        //R
    void deleteById(Long id);   //D

    List<Run> finByMembersLimit(Integer min, Integer max);

}
