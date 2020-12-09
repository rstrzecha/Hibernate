package pl.sdacademy.database.daoimpl;

import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.database.dao.RunMemberDao;
import pl.sdacademy.database.entity.Run;
import pl.sdacademy.database.entity.RunMember;
import pl.sdacademy.database.utils.HibernateUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RunMemberDaoImplTest {

    private RunMemberDao runMemberDao = new RunMemberDaoImpl();

    @BeforeEach
    void cleanTable() {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        session
                .createSQLQuery("DELETE FROM RUN_MEMBER")
                .executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    @Test
    void save() {
        //GIVEN
        RunMember runMember = new RunMember();
        runMember.setName("Usain Bolt");
        runMember.setStartNumber(15);

        //WHEN
        runMemberDao.save(runMember);

        RunMember saved = runMemberDao.findById(runMember.getId());

        //THEN
        assertNotNull(saved);
        assertEquals(runMember.getId(), saved.getId());
        assertEquals(runMember.getName(), saved.getName());
        assertEquals(runMember.getStartNumber(), saved.getStartNumber());
    }

    @Test
    void findAll() {
        //GIVEN
        RunMember runMember1 = new RunMember();
        runMember1.setName("Usain Bolt");
        runMember1.setStartNumber(15);
        runMemberDao.save(runMember1);
        RunMember runMember2 = new RunMember();
        runMember2.setName("Asafa Powell");
        runMember2.setStartNumber(14);
        runMemberDao.save(runMember2);

        //WHEN
        List<RunMember> resultList = runMemberDao.findAll();

        //THEN
        assertNotNull(resultList);
        assertEquals(2, resultList.size());
    }

    @Test
    void deleteById() {
        //GIVEN
        RunMember runMember = new RunMember();
        runMember.setName("Usain Bolt");
        runMember.setStartNumber(15);
        runMemberDao.save(runMember);

        //WHEN
        runMemberDao.deleteById(runMember.getId());
        RunMember deleted =runMemberDao.findById(runMember.getId());

        //THEN
        assertNull(deleted);
    }
}