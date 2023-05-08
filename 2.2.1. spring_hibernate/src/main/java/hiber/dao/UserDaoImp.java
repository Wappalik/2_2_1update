package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;
   private Session session;


   @Override
   public void add(User user) {
       sessionFactory.getCurrentSession().save(user);

   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
         TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
         return query.getResultList();
   }

    @Override
    public User getUserByCar(String model, int series) {
        try {
            String hql = "FROM User WHERE car.model = :model AND car.series = :series";
            TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
            query.setParameter("model", model);
            query.setParameter("series", series);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ошибка поиска пользователя по машине: " + e);
            return null;
        }
    }


}