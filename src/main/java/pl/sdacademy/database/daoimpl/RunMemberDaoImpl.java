package pl.sdacademy.database.daoimpl;

import org.hibernate.Session;
import pl.sdacademy.database.dao.RunMemberDao;
import pl.sdacademy.database.entity.Run;
import pl.sdacademy.database.entity.RunMember;
import pl.sdacademy.database.utils.HibernateUtils;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class RunMemberDaoImpl implements RunMemberDao {
    @Override
    public void save(RunMember runMember) {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        session.saveOrUpdate(runMember);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public RunMember findById(Long id) {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        RunMember runMember = null;

        try {
            runMember = session
                    .createQuery("from RunMember where id=:id", RunMember.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {}

        session.getTransaction().commit();
        session.close();

        return runMember;
    }

    @Override
    public List<RunMember> findAll() {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        List<RunMember> runMembers = new ArrayList<>();

        runMembers = session
               .createQuery("from RunMember", RunMember.class)
               .list();

        session.getTransaction().commit();
        session.close();

        return runMembers;
    }

    @Override
    public void deleteById(Long id) {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        session
               .createQuery("delete RunMember where id=:id")
               .setParameter("id", id)
                .executeUpdate();

        session.getTransaction().commit();
        session.close();
    }
}