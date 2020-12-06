package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Зил", "131")));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("LADA", "2105")));
      userService.add(new User("Junior", "junich", "junior@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("LADA", "Priora")));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("ЗаЗ", "151")));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }

      System.out.println("User найденый по моедли и серии автомобиля " + userService.findOwner("Зил", "131"));
      userService.deleteAllUsers();
      context.close();
   }
}

//Задание:
//
//  2. Создайте сущность Car с полями String model и int series, на которую будет ссылаться User с помощью связи one-to-one.
//  3. Добавьте этот класс в настройки hibernate.
//  4. Создайте несколько пользователей с машинами, добавьте их в базу данных, вытащите обратно.
//  5. В сервис добавьте метод, который с помощью hql-запроса будет доставать юзера, владеющего машиной по ее модели и серии.
//http://internetka.in.ua/hibernate-one-to-one/
//https://www.codeflow.site/ru/article/jpa-one-to-one
//https://javarush.ru/groups/posts/1982-jpa-entities--db-relationships
//https://www.baeldung.com/jpa-one-to-one
//http://java-online.ru/hibernate-hql.xhtml

