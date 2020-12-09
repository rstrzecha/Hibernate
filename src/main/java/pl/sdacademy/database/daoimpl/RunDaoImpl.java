package pl.sdacademy.database.daoimpl;


import org.hibernate.Session;
import pl.sdacademy.database.dao.RunDao;
import pl.sdacademy.database.entity.Run;
import pl.sdacademy.database.utils.HibernateUtils;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class RunDaoImpl implements RunDao {
    @Override
    public void save(Run run) {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        session.saveOrUpdate(run);          //a automatu ma taką metodę
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Run findById(Long id) {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        Run run = null;

        try {
            run = session
                    .createQuery("from Run where id=:id", Run.class) //zrzutuje Object do klasy Run
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {}

        session.getTransaction().commit();
        session.close();

        return run;
    }

    @Override
    public List<Run> findAll() {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        List<Run> run = new ArrayList<>();

        try {

            run = session
                    .createQuery("from Run", Run.class)
                    .list();

        } catch (NoResultException e) {}

        session.getTransaction().commit();
        session.close();

        return run;
    }

    @Override
    public void deleteById(Long id) {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();


        session
                .createQuery("delete Run where id=:id")
                .setParameter("id", id)
                .executeUpdate();


        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Run> finByMembersLimit(Integer min, Integer max) {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        List<Run> run = new ArrayList<>();

        run = session
                .createQuery("from Run where membersLimit between :min and :max", Run.class)
                .setParameter("min", min)
                .setParameter("max", max)
                .list();

        return run;
    }
}