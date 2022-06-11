package hospital.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "diseases")
public class Disease implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToMany(
            mappedBy = "disease",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Diagnosis> diagnoses = new ArrayList<>();

    public Disease() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Disease{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Disease(String name, List<Diagnosis> diagnoses) {
        this.name = name;
        this.diagnoses = diagnoses;
    }
}

//getter i setter dla name i diagnosis + contruktor
