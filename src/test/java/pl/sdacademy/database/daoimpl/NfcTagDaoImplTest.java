package pl.sdacademy.database.daoimpl;

import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.database.dao.NfcTagDao;
import pl.sdacademy.database.entity.NfcTag;
import pl.sdacademy.database.entity.Run;
import pl.sdacademy.database.utils.HibernateUtils;

import static org.junit.jupiter.api.Assertions.*;

class NfcTagDaoImplTest {

    private NfcTagDao nfcTagDao = new NfcTagDaoImpl();

    @BeforeEach
    void cleanTable() {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        session
                .createSQLQuery("DELETE FROM NFC_TAG")
                .executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    @Test
    void save() {
        //GIVEN
        NfcTag nfcTag = new NfcTag();
        nfcTag.setSerial("123");

        //WHEN
        nfcTagDao.save(nfcTag);

        NfcTag saved = nfcTagDao.findById(nfcTag.getId());

        //THEN
        assertNotNull(saved);
        assertEquals(nfcTag.getId(), saved.getId());
        assertEquals(nfcTag.getSerial(), saved.getSerial());
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void deleteById() {
    }
}