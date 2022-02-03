package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement st = getConnection().createStatement()) {
            st.execute("CREATE TABLE `db`.`db` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT, \n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `lastName` VARCHAR(45) NOT NULL,\n" +
                    "  `age` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);\n");
        } catch (SQLException ignored) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement st = getConnection().createStatement()) {
            st.execute("DROP TABLE `db`");
        } catch (SQLException ignored) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection stt = getConnection()) {
            PreparedStatement st = stt.prepareStatement(
                    "insert into `db` (name,lastName,age) values (?,?,?)"
            );
            st.setString(1, name);
            st.setString(2, lastName);
            st.setByte(3, age);
            st.executeUpdate();
            System.out.println("User с именем – "+name+" добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection stt = getConnection()) {
            PreparedStatement st = stt.prepareStatement(
                    "delete from `db` where id=?"
            );
            st.setLong(1, id);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
         List<User> users = new ArrayList<>();
        try (Statement st = getConnection().createStatement()) {
            ResultSet resultSet = st.executeQuery("select * from db");
            while (resultSet.next()){
                User user = new User();
                user.setId( resultSet.getLong("id"));
                user.setName( resultSet.getString("name"));
                user.setLastName( resultSet.getString("lastName"));
                user.setAge( resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException ignored) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement st = getConnection().createStatement()) {
            st.execute("truncate TABLE db");
        } catch (SQLException ignored) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
