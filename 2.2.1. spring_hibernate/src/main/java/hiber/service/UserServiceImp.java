package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Autowired
   private LocalSessionFactoryBean getSessionFactory;

   @Autowired
   private SessionFactory sessionFactory;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Override
   @Transactional
   public User getUserByCarModelAndSeries(String model, int series) {
      Session session = sessionFactory.getCurrentSession();
      String hql = "FROM User u WHERE u.car.model =: model AND u.car.series =: series";
      Query<User> query = session.createQuery(hql, User.class);
      query.setParameter("model", model);
      query.setParameter("series", series);
      return query.uniqueResult();

   }

}
