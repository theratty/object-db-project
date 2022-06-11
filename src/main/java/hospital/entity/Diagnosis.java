package hospital.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
@Entity
@Table(name = "diagnoses")
public class Diagnosis implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;

    private String diagnosis;

    private Date diagnosisDate;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Disease disease;

    public Diagnosis(String diagnosis, Date diagnosis_date, Doctor doctor) {
        this.diagnosis = diagnosis;
        this.diagnosisDate = diagnosis_date;
        this.doctor = doctor;
    }

    public Diagnosis(){

    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Date getDiagnosisDate() {
        return diagnosisDate;
    }

    public void setDiagnosisDate(Date diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "id=" + id +
                ", diagnosis='" + diagnosis + '\'' +
                ", diagnosis_date=" + diagnosisDate +
                ", doctor=" + doctor +
                ", disease=" + disease +
                '}';
    }
}
