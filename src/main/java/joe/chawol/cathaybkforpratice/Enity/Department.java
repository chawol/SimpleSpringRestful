package joe.chawol.cathaybkforpratice.Enity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DEPARTMENT")
@Getter
@Setter
public class Department {
    @Id
    private String DEPID;
    private String DEPNAME;

    public Department(String DEPID, String DEPNAME) {
        this.DEPID = DEPID;
        this.DEPNAME = DEPNAME;
    }

    public Department() {
    }
}