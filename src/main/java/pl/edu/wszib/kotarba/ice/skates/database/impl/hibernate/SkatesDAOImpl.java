package pl.edu.wszib.kotarba.ice.skates.database.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.kotarba.ice.skates.database.ISkatesDAO;
import pl.edu.wszib.kotarba.ice.skates.model.Skates;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class SkatesDAOImpl implements ISkatesDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Skates> getSkates() {
        Session session = this.sessionFactory.openSession();
        Query<Skates> query = session.createQuery("FROM pl.edu.wszib.kotarba.ice.skates.model.Skates");
        List<Skates> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public Optional<Skates> getSkatesById(int skatesId) {
        Session session = this.sessionFactory.openSession();
        Query<Skates> query = session.createQuery("FROM pl.edu.wszib.kotarba.ice.skates.model.Skates WHERE id = :id");
        query.setParameter("id", skatesId);
        try {
            Skates skates = query.getSingleResult();
            session.close();
            return Optional.of(skates);
        } catch (NoResultException e) {
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public void updateSkates(Skates skates) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(skates);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

}
