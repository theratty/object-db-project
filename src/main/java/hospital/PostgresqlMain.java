package hospital;

import hospital.entity.*;
import hospital.hibernate.HibernateConnector;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PostgresqlMain {
    public static void main(String[] args) {
        Session session = HibernateConnector.getInstance().getSession();


        System.out.println("Session created!");

        session.close();
    }
}