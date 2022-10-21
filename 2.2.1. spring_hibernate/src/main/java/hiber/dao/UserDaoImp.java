package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private SessionFactory sessionFactory;

   @Autowired
   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }
   public void add(Car car) { sessionFactory.getCurrentSession().save(car); }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<Car> listCars() {
      TypedQuery<Car> query=sessionFactory.getCurrentSession().createQuery("from Car");
      return query.getResultList();
   }

   @Override
   public User findUser(String model, int series) {
      Session session = sessionFactory.getCurrentSession();
      User user = new User();
      for (Car car : listCars()) {
         if (car.getModel().equals(model) && car.getSeries() == series) {
            long x = car.getId();
            String hql = "from User where id = :paramName";
            Query query = session.createQuery(hql);
            query.setParameter("paramName", x);
            user = (User) query.getSingleResult();
         }
      }
      return user;
   }
}
