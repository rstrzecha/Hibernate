package pl.sdacademy.database.dao;

import pl.sdacademy.database.entity.NfcTag;

import java.util.List;

public interface NfcTagDao {

    void save(NfcTag nfcTag);   //C U    // nie potrzeba update, bo tutaj to zrobimy
    NfcTag findById(Long id);   //R
    List<NfcTag> findAll();     //R
    void deleteById(Long id);   //D
}
