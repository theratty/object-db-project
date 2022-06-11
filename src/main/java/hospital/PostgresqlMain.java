package hospital;

import hospital.hibernate.HibernateConnector;
import org.hibernate.Session;

public class PostgresqlMain {
    public static void main(String[] args) {
        Session session = HibernateConnector.getInstance().getSession();


        System.out.println("Session created!");

        session.close();
    }
}