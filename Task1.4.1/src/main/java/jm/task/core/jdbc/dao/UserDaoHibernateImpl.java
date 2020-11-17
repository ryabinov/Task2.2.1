package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;


import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    private SessionFactory sessionFactory = Util.getSessionFactory();


    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() throws SQLException {

        try {
            sessionFactory.getCurrentSession().beginTransaction();
            sessionFactory.getCurrentSession().createSQLQuery("CREATE TABLE IF NOT EXISTS user" +
                    "(id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(50)," +
                    "lastName VARCHAR(50)," +
                    "age INT)").executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();
            System.out.println("Таблица добавлена");
        } catch (HibernateException e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {

        try {
            sessionFactory.getCurrentSession().beginTransaction();
            sessionFactory.getCurrentSession().createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();
            System.out.println("Таблица удалена");
        } catch (Exception e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try {
            sessionFactory.getCurrentSession().beginTransaction();
            sessionFactory.getCurrentSession().persist(new User(name, lastName, age));
            sessionFactory.getCurrentSession().getTransaction().commit();
            System.out.println("User добавлен в таблицу!!!" + "\n");
        } catch (Exception e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {

        try {
            sessionFactory.getCurrentSession().beginTransaction();
            User user = (User) sessionFactory.getCurrentSession().load(User.class, id);
            sessionFactory.getCurrentSession().delete(user);
            sessionFactory.getCurrentSession().getTransaction().commit();
            System.out.println(user + " удалет по id: " + id + "\n");
        } catch (Exception e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            userList = sessionFactory.getCurrentSession().createQuery("from User").list();
            sessionFactory.getCurrentSession().getTransaction().commit();
            System.out.println("Users находящиеся в таблицы: " + userList + "\n");
        } catch (Exception e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            e.printStackTrace();
        }

        return userList;
    }


    @Override
    public void cleanUsersTable() {

        try {
            sessionFactory.getCurrentSession().beginTransaction();

            List<User> userList = sessionFactory.getCurrentSession().createQuery("from User").list();
            for (User u : userList) {
                sessionFactory.getCurrentSession().delete(u);
                System.out.println(u + " Удален!" + "\n");
            }
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (Exception e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            e.printStackTrace();
        }
    }
}


//://www.journaldev.com/3531/spring-mvc-hibernate-mysql-integration-crud-example-tutorial