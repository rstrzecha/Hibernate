package pl.sdacademy.database.daoimpl;

import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.database.dao.RunDao;
import pl.sdacademy.database.entity.Run;
import pl.sdacademy.database.utils.HibernateUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RunDaoImplTest {

    private RunDao runDao = new RunDaoImpl();

    @BeforeEach
    void cleanTable() {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        session
                .createSQLQuery("DELETE FROM RUN")
                .executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    @Test
    void save() {
        //GIVEN
        Run run = new Run();
        run.setName("Testowa nazwa");
        run.setCity("Rzeszów");
        run.setMembersLimit(99);

        //WHEN
        runDao.save(run);

        Run saved = runDao.findById(run.getId());

        //THEN
        assertNotNull(saved);
        assertEquals(run.getId(), saved.getId());
        assertEquals(run.getName(), saved.getName());
    }

    @Test
    void findAll() {
        //GIVEN
        Run run1 = new Run();
        run1.setName("Testowa nazwa");
        run1.setCity("Rzeszów");
        run1.setMembersLimit(99);
        runDao.save(run1);
        Run run2 = new Run();
        run2.setName("Druga nazwa testowa");
        run2.setCity("Lublin");
        run2.setMembersLimit(87);
        runDao.save(run2);

        //WHEN
        List<Run> resultList = runDao.findAll();

        //THEN
        assertNotNull(resultList);
        assertEquals(2, resultList.size());
    }

    @Test
    void deleteById() {
        //GIVEN
        Run run = new Run();
        run.setName("Wyścig do usunięcia");
        run.setCity("Wólka");
        run.setMembersLimit(77);
        runDao.save(run);

        //WHEN
        runDao.deleteById(run.getId());
        Run deleted =runDao.findById(run.getId());

        //THEN
        assertNull(deleted);
    }


    @Test
    void finByMembersLimit() {
        //GIVEN
        Run run1 = new Run();
        run1.setName("Testowa nazwa");
        run1.setCity("Rzeszów");
        run1.setMembersLimit(99);
        runDao.save(run1);
        Run run2 = new Run();
        run2.setName("Druga nazwa testowa");
        run2.setCity("Lublin");
        run2.setMembersLimit(78);
        runDao.save(run2);

        //WHEN
        List<Run> resultList = runDao.finByMembersLimit(70, 80);

        //THEN
        assertNotNull(resultList);
        assertEquals(1, resultList.size());

    }
}