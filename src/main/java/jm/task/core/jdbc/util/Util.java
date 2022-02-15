package jm.task.core.jdbc.util;


import com.fasterxml.classmate.AnnotationConfiguration;
import com.mysql.jdbc.Driver;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.MySQL8Dialect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Util {
    private static StandardServiceRegistry standardServiceRegistry;
    private static SessionFactory sessionFactory;
    private static final String URL = "jdbc:mysql://localhost:3306/db";
    private static final String LOG = "root";
    private static final String PASS = "root";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static Connection connection;
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return connection = DriverManager.getConnection(URL, LOG, PASS);
    }
    static {
        Properties prop= new Properties();

        prop.setProperty("hibernate.connection.url", URL);
        prop.setProperty("hibernate.connection.username", LOG);
        prop.setProperty("hibernate.connection.password", PASS);
        prop.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        prop.setProperty("hibernate.show_sql","true");
        prop.setProperty("hibernate.hbm2ddl.auto","none");
        prop.setProperty("show_sql", "true"); //If you wish to see the generated sql query

         sessionFactory = new Configuration().addProperties(prop).addAnnotatedClass(User.class).buildSessionFactory();
    }
    public static SessionFactory getSessionFactory() throws ClassNotFoundException, SQLException {
        return sessionFactory;
    }

}
