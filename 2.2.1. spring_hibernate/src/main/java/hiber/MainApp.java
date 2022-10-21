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

      Car car1 = new Car(11, "LADA_CALINA");
      Car car2 = new Car(12, "LADA_GRANTA");
      Car car3 = new Car(13, "LADA_VESTA");
      Car car4 = new Car(14, "LADA_PRIORA");
      User user1 = new User("User1", "Lastname1", "user1@mail.ru", car1);
      User user2 = new User("User2", "Lastname2", "user2@mail.ru",car2);
      User user3 = new User("User3", "Lastname3", "user3@mail.ru",car3);
      User user4 = new User("User4", "Lastname4", "user4@mail.ru",car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " +user.getEmpCar());
         System.out.println();
      }

      System.out.println(userService.findUser("LADA_PRIORA", 14));

      context.close();
   }
}
