package jm.task.core.jdbc.dao;

import com.sun.jdi.connect.Connector;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;


import javax.persistence.criteria.CriteriaBuilder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    private SessionFactory sessionFactory;


    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() throws SQLException{

        Session session = session = Util.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS user"+
                "(id INT PRIMARY KEY," +
                "name VARCHAR(50)," +
                "lastName VARCHAR(50)," +
                "age INT)");
            query.executeUpdate();
            transaction.commit();
            session.close();
           // System.out.println("создал таблицу user");
        } catch (HibernateException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String dropUT = "DROP TABLE IF EXISTS user";
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            SQLQuery sqlQuery = session.createSQLQuery(dropUT);
          //  System.out.println("Удалил таблицу hiber" +"\n");
            sqlQuery.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            //System.out.println(user + " добавлен в таблицу!!!" +"\n");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            User user = (User) session.load(User.class, id);
            session.delete(user);
            transaction.commit();
          //  System.out.println(user + " удалет по id: "+ id +"\n");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        List<User> userList = session.createQuery("from User").list();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            for (User u : userList) {
                //   System.out.println("Users находящиеся в таблицы: " + u +"\n");
            }

        } catch (Exception e) {if (transaction != null) {
            transaction.rollback();
        }
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        Session session = Util.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            List<User> userList = session.createQuery("from User").list();
            for(User u : userList){
                session.delete(u);
              //  System.out.println(u+ " Удален!" +"\n");
            }

            transaction.commit();


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}




//://www.journaldev.com/3531/spring-mvc-hibernate-mysql-integration-crud-example-tutorial