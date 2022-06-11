package hospital.hibernate;

import hospital.entity.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnector {

    private static HibernateConnector me;
    private Configuration cfg;
    private SessionFactory sessionFactory;

    private HibernateConnector() throws HibernateException {

// build the config
        cfg = new Configuration();


        cfg.addAnnotatedClass(Department.class);
        cfg.addAnnotatedClass(Diagnosis.class);
        cfg.addAnnotatedClass(Disease.class);
        cfg.addAnnotatedClass(Doctor.class);
        cfg.addAnnotatedClass(Patient.class);

        cfg.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        cfg.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres");
        cfg.setProperty("hibernate.connection.username", "postgres");
        cfg.setProperty("hibernate.hbm2ddl.auto", "create");
        cfg.setProperty("hibernate.connection.password", "password123");
        cfg.setProperty("hibernate.javax.persistence.create-database-schemas", "true");
        cfg.setProperty("hibernate.show_sql", "true");

        sessionFactory = cfg.buildSessionFactory();
    }

    public static synchronized HibernateConnector getInstance() throws HibernateException {
        if (me == null) {
            me = new HibernateConnector();
        }

        return me;
    }

    public Session getSession() throws HibernateException {
        Session session = sessionFactory.openSession();
        if (!session.isConnected()) {
            this.reconnect();
        }
        return session;
    }

    private void reconnect() throws HibernateException {
        this.sessionFactory = cfg.buildSessionFactory();
    }
}
