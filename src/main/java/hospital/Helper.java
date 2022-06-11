package hospital;


import hospital.entity.*;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Helper {

    static Random random = new Random();


    static public String generate(String prefix, Integer number) {
        return prefix + number;
    }

    static public Integer generateNum() {
        return random.nextInt(9);
    }

    static public Date generateDate(Integer year) {
        Integer month = random.nextInt(12) + 1;
        Integer day = random.nextInt(27) + 1;
        return new Date(year, month, day);
    }

    static public String generateSex() {
        Integer sex = random.nextInt(2);
        if (sex == 0) {
            return "Male";
        } else {
            return "Female";
        }
    }
    static public String generateDrug(){
        Integer drug = random.nextInt(4);
        String[] drugs = {"Drug1", "Drug2", "Drug3", "Drug4"};
        return drugs[drug];
    }

    static public void generateDateBase(EntityManager em) {
        em.getTransaction().begin();

        for (int i = 0; i < 1000; i++) {
            Department department = new Department(Helper.generate("Department", i), Helper.generateNum(), null, null);
            List<Patient> patients = new ArrayList<Patient>();
            for (int j = 0; j < Helper.generateNum(); j++) {
                Patient patient = new Patient(Helper.generate("Patient", j), Helper.generateDate(2000), Helper.generateSex(), Helper.generateDate(2020), Helper.generateNum(), Helper.generateDrug());
                patients.add(patient);
                patient.setDepartment(department);
            }
            department.setPatients(patients);
            Doctor doctor = new Doctor(Helper.generate("Doctor", i), Helper.generate("Speciality", i), department);
            department.setDoctor(doctor);

            List<Diagnosis> diagnoses = new ArrayList<Diagnosis>();

            for (int j = 0; j < patients.size(); j++) {
                Diagnosis diagnosis = new Diagnosis(Helper.generate("Diagnosis", j), Helper.generateDate(2021), doctor);
                diagnoses.add(diagnosis);
                patients.get(j).setDiagnosis(diagnosis);
            }
            doctor.setDiagnoses(diagnoses);
            Disease disease = new Disease(Helper.generate("Disease", i), diagnoses);
            for (Diagnosis diagnosis : diagnoses) {
                diagnosis.setDisease(disease);
            }

            em.persist(department);
            em.persist(doctor);
            for (Patient patient : patients) {
                em.persist(patient);
            }
            for (Diagnosis diagnosis : diagnoses) {
                em.persist(diagnosis);
            }
            em.persist(disease);
        }


        em.getTransaction().commit();
    }


    static public void generateRelationalDatabase(Session session) {
        session.beginTransaction();

        for (int i = 0; i < 1000; i++) {
            Department department = new Department(Helper.generate("Department", i), Helper.generateNum(), null, null);
            List<Patient> patients = new ArrayList<Patient>();
            for (int j = 0; j < Helper.generateNum(); j++) {
                Patient patient = new Patient(Helper.generate("Patient", j), Helper.generateDate(2000), Helper.generateSex(), Helper.generateDate(2020), Helper.generateNum(), Helper.generateDrug());
                patients.add(patient);
                patient.setDepartment(department);
            }
            department.setPatients(patients);
            Doctor doctor = new Doctor(Helper.generate("Doctor", i), Helper.generate("Speciality", i), department);
            department.setDoctor(doctor);

            List<Diagnosis> diagnoses = new ArrayList<Diagnosis>();

            for (int j = 0; j < patients.size(); j++) {
                Diagnosis diagnosis = new Diagnosis(Helper.generate("Diagnosis", j), Helper.generateDate(2021), doctor);
                diagnoses.add(diagnosis);
                patients.get(j).setDiagnosis(diagnosis);
            }
            doctor.setDiagnoses(diagnoses);
            Disease disease = new Disease(Helper.generate("Disease", i), diagnoses);
            for (Diagnosis diagnosis : diagnoses) {
                diagnosis.setDisease(disease);
            }

            session.persist(department);
            session.persist(doctor);
            for (Patient patient : patients) {
                session.persist(patient);
            }
            for (Diagnosis diagnosis : diagnoses) {
                session.persist(diagnosis);
            }
            session.persist(disease);
        }


        session.getTransaction().commit();
    }


}
