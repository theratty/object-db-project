package hospital;

import hospital.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PostgresqlMain {
    public static void main(String[] args) {
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("$objectdb/db/hospital.odb");
        EntityManager em = emf.createEntityManager();

        // Store 1000 Point objects in the database:
        em.getTransaction().begin();

        Department department_1 = new Department("jeden", 4, null,null );

        List<Patient> patients = new ArrayList<Patient>();
        patients.add(new Patient("Name1", new Date(2000, 1, 1), "sex1", new Date(2021, 2, 3), 5));
        patients.add(new Patient("Name2", new Date(1990, 1, 1), "sex2", new Date(2022, 4, 5), 10));

        department_1.setPatients(patients);
        patients.get(0).setDepartment(department_1);
        patients.get(1).setDepartment(department_1);

        Doctor doctor = new Doctor("doctor1", "speciality1", department_1);
        department_1.setDoctor(doctor);

        patients.get(0).setDiagnosis(new Diagnosis("diagnosis1", new Date(2019, 1, 1), doctor));
        patients.get(1).setDiagnosis(new Diagnosis("diagnosis2", new Date(2019, 1, 1), doctor));

        List<Diagnosis> diagnoses = new ArrayList<Diagnosis>();
        diagnoses.add(patients.get(0).getDiagnosis());
        diagnoses.add(patients.get(1).getDiagnosis());

        doctor.setDiagnoses(diagnoses);

        Disease disease = new Disease("disease1", diagnoses);

        diagnoses.get(0).setDisease(disease);
        diagnoses.get(1).setDisease(disease);

        em.persist(department_1);
        em.persist(doctor);
        em.persist(patients.get(0));
        em.persist(patients.get(1));

        em.persist(diagnoses.get(0));
        em.persist(diagnoses.get(1));

        em.persist(disease);

        em.getTransaction().commit();

//        // Find the number of Point objects in the database:
//        Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
//        System.out.println("Total Points: " + q1.getSingleResult());
//
//        // Find the average X value:
//        Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
//        System.out.println("Average X: " + q2.getSingleResult());
//
//        // Retrieve all the Point objects from the database:
//        TypedQuery<Point> query =
//            em.createQuery("SELECT p FROM Point p", Point.class);
//        List<Point> results = query.getResultList();
//        for (Point p : results) {
//            System.out.println(p);
//        }

        // Close the database connection:
        em.close();
        emf.close();
    }
}