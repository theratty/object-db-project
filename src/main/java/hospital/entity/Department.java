package hospital.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;

    private String departmentName;

    private Integer numOfBeds;

    @OneToOne
    private Doctor doctor;

    public Department() {
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getNumOfBeds() {
        return numOfBeds;
    }

    public void setNumOfBeds(Integer numOfBeds) {
        this.numOfBeds = numOfBeds;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    @OneToMany(
            mappedBy = "department",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Patient> patients = new ArrayList<>();

    public Department(String department_name, Integer number_of_beds, Doctor doctor, List<Patient> patients) {
        this.departmentName = department_name;
        this.numOfBeds = number_of_beds;
        this.doctor = doctor;
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", department_name='" + departmentName + '\'' +
                ", number_of_beds=" + numOfBeds +
                ", patients=" + patients +
                ", doctor=" + doctor +
                '}';
    }
}
