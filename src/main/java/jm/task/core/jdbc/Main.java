package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        User user = new User("Sasha","Borisov", (byte) 23);
        User user1 = new User("Misha","Kirgincev", (byte) 32);
        User user2 = new User("Boris","Volkov", (byte) 43);
        User user3 = new User("Kirill","Matvienko", (byte) 34);
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser(user.getName(), user.getLastName(), user.getAge());
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
