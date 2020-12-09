package pl.sdacademy.database.daoimpl;


import org.hibernate.Session;
import pl.sdacademy.database.dao.NfcTagDao;
import pl.sdacademy.database.entity.NfcTag;
import pl.sdacademy.database.entity.Run;
import pl.sdacademy.database.utils.HibernateUtils;

import javax.persistence.NoResultException;
import java.util.List;

public class NfcTagDaoImpl implements NfcTagDao {
    @Override
    public void save(NfcTag nfcTag) {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        session.saveOrUpdate(nfcTag);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public NfcTag findById(Long id) {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        NfcTag nfcTag = null;

        try {
            nfcTag = session
                    .createQuery("from NfcTag where id=:id", NfcTag.class) //zrzutuje Object do klasy Run
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {}

        session.getTransaction().commit();
        session.close();

        return nfcTag;
    }

    public List<NfcTag> findAll() {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        List<NfcTag> result = session
                .createQuery("from NfcTag", NfcTag.class)
                .list();

        session.getTransaction().commit();
        session.close();

        return result;
    }

    public void deleteById(Long id) {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        session
                .createQuery("delete NfcTag where id=:id")
                .setParameter("id", id)
                .executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

}
