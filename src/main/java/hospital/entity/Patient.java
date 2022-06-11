package hospital.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "patients")
public class Patient implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;

    private String name;
    private Date birthDate;
    private String sex;
    private Date hospitalizationDate;
    private Integer drugsReceived;
    private String drug;

    @OneToOne
    private Diagnosis diagnosis;

    @ManyToOne
    private Department department;

    public Patient() {
    }

    public Patient(String name, Date birthDate, String sex, Date hospitalizationDate, Integer drugsReceived, String drug) {
        this.name = name;
        this.birthDate = birthDate;
        this.sex = sex;
        this.hospitalizationDate = hospitalizationDate;
        this.drugsReceived = drugsReceived;
        this.drug = drug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getHospitalizationDate() {
        return hospitalizationDate;
    }

    public Patient(String drug) {
        this.drug = drug;
    }

    public void setHospitalizationDate(Date hospitalizationDate) {
        this.hospitalizationDate = hospitalizationDate;
    }

    public Integer getDrugsReceived() {
        return drugsReceived;
    }

    public void setDrugsReceived(Integer drugsReceived) {
        this.drugsReceived = drugsReceived;
    }


    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", sex='" + sex + '\'' +
                ", hospitalizationDate=" + hospitalizationDate +
                ", drugsReceived=" + drugsReceived +
                ", drug='" + drug + '\'' +
                ", department=" + department +
                '}';
    }
}
