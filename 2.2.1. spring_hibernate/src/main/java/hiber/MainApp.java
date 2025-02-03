package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User();
      user1.setFirstName("Oleg");
      user1.setLastName("Anisimov");
      user1.setEmail("OpenkinOleg@bk.ru");
      Car car1 = new Car();
      car1.setModel("Lada");
      car1.setSeries(2109);
      user1.setCar(car1);
      userService.add(user1);

      User user2 = new User();
      user2.setFirstName("Yan");
      user2.setLastName("Suroviy");
      user2.setEmail("GryaznieVolosi@su.ru");
      Car car2 = new Car();
      car2.setModel("Ural");
      car2.setSeries(777);
      user2.setCar(car2);
      userService.add(user2);

//      User user3 = new User();
//      user3.setFirstName("Vladimir");
//      user3.setLastName("Puchin");
//      user3.setEmail("Gosslugba@poKontrakty.com");
//      Car car3 = new Car();
//      car3.setModel("Rolls-royse");
//      car3.setSeries(1);
//      user1.setCar(car3);
//      userService.add(user3);

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("User: " + user.getFirstName() + " " + user.getLastName() + ", Email: " +
                 user.getEmail() + ", Car: " + user.getCar().getModel() + "Series: " + user.getCar().getSeries());
      }

      context.close();
   }
}
